---
weight: 1
slug: index
date: 2024-09-20
title: "샘플코드"
description: "샘플코드"
toc: true
---


## 임베딩 프로세스

파이썬 실행 후 chroma 폴더에 파일들 임베딩 됨.

```
import os

from langchain_community.document_loaders import PyMuPDFLoader, Docx2txtLoader, TextLoader, UnstructuredExcelLoader
from langchain.text_splitter import RecursiveCharacterTextSplitter

from langchain_community.embeddings import HuggingFaceEmbeddings
from langchain.vectorstores import Chroma

# 임베딩 설정
embeddings = HuggingFaceEmbeddings(
    model_name="BAAI/bge-m3",
    model_kwargs={'device': 'cpu'},  # 또는 'cuda'로 변경하여 GPU를 사용할 수 있음
    encode_kwargs={'normalize_embeddings': True}  # 임베딩 정규화
)

# Chroma 클라이언트 초기화
collection_name = 'DAIA'
db = Chroma(
    persist_directory='./chroma',
    collection_name=collection_name
)
# Chroma 데이터베이스 컬렉션 삭제
db.delete_collection()

# PDF
files = [file for file in os.listdir('./dataset')]
for file in files:
    file_path = os.path.join('./dataset', file)
    print('>>> file_path', file_path)

    try:
        ''''''
        if file.endswith('.pdf') :
            loader = PyMuPDFLoader(file_path)

        if file.endswith('.docx') or file.endswith('.doc') :
            loader = Docx2txtLoader(file_path)
            
        if file.endswith('.txt') :
            loader = TextLoader(file_path)

        if file.endswith('.xlsx') or file.endswith('.xls') :
            loader = UnstructuredExcelLoader(file_path, mode="elements")

        pages = loader.load()
        text_splitter = RecursiveCharacterTextSplitter(chunk_size=500, chunk_overlap=50)
        texts = text_splitter.split_documents(pages)

        # 텍스트와 임베딩 확인
        print(f"텍스트 수 : {len(texts)}")
        if len(texts) > 0 :
            ''''''

            # 메타데이터 문자열 변환
            for text in texts:
                if isinstance(text.metadata.get('languages'), list):
                    text.metadata['languages'] = ', '.join(text.metadata['languages'])

            # Chroma 데이터베이스 생성
            db.from_documents(
                texts,
                embeddings,
                persist_directory='./chroma',
                collection_name=collection_name
            )

    except Exception as e:
        print(f"Error {file} : {e}")

```

## 프론트 챗봇 페이지

프론트 서버 실행
streamlit run front.py

```
import os
import time

import requests
import streamlit as st
from dotenv import load_dotenv

load_dotenv()

API_URL = os.getenv('API_URL')
API_URL = 'http://localhost:8000'

def request_chat_api(user_message: str) -> str:
    url = f"{API_URL}/chat"
    resp = requests.post(
        url,
        json={
            "user_message": user_message,
        },
    )
    # 파이선 dict로 역직렬화
    resp = resp.json()
    resp = resp["answer"]
    print(resp)
    return resp

def init_streamlit():
    st.title("DA IA 챗봇")

    # initial chat history
    if "messages" not in st.session_state:
        st.session_state.messages = []

    # 채팅 메세지 보여주기
    for message in st.session_state.messages:
        with st.chat_message(message['role']):
            st.markdown(message['content'])

def chat_main():
    # chat_input을 message에 할당하되, 빈 문자열일때만(엔터만 눌렀을때) 조건문
    if message := st.chat_input(""):
        # 사용자 메세지를 chat history에 추가
        st.session_state.messages.append({"role": "user", "content": message})

        # 유저 메세지 보여주기
        with st.chat_message("user"):
            st.markdown(message)

        # api대답 받아서 보여주기 > chat api 호출
        assistant_response = request_chat_api(message)

        # 챗봇 메세지를 chat history에 추가
        with st.chat_message("assistant"):
            message_placeholder = st.empty()
            full_response = ""
            for lines in assistant_response.split("\n"):
                for chunkl in lines.split():
                    full_response += chunkl + " "
                    time.sleep(0.05)
                    # add a blinking cursor
                    message_placeholder.markdown(full_response)
                full_response += "\n"
            message_placeholder.markdown(full_response)

        st.session_state.messages.append(
            {"role": "assistant", "content": full_response}
        )

if __name__ == "__main__":
    init_streamlit()
    chat_main()
```

## 백엔드 API / LLM, RAG

백엔드 실행 
uvicorn api:app --reload

```
import os
import time
from typing import Dict
import datetime

# 설정
from langchain_google_genai import ChatGoogleGenerativeAI # llm
from langchain_core.prompts import ChatPromptTemplate, MessagesPlaceholder # 프롬프트
from langchain_community.embeddings import HuggingFaceEmbeddings # 허깅페이스 임베딩
from langchain.vectorstores import Chroma # 벡터 DB
# 메모리
from langchain_core.runnables import ConfigurableFieldSpec
from langchain_core.runnables.history import RunnableWithMessageHistory
from langchain_core.chat_history import BaseChatMessageHistory
from langchain_community.chat_message_histories import ChatMessageHistory
# 체인
from langchain.schema.runnable import RunnablePassthrough
from langchain.schema.output_parser import StrOutputParser
from fastapi import FastAPI
from pydantic import BaseModel
# 캐시
from langchain.globals import set_llm_cache
from langchain.cache import InMemoryCache

# nest_asyncio 적용
import nest_asyncio
nest_asyncio.apply()

# 캐시 적용
set_llm_cache(InMemoryCache())

### 1. 셋팅
# LLM
llm = ChatGoogleGenerativeAI(
    # model="gemini-pro",
    model="gemini-1.5-pro-latest",
    google_api_key="AIzaSyDGuiujLz0sb927K6p8NoRzUxFEdVmHnDc"
)

# prompt = ChatPromptTemplate.from_messages(template)
prompt = ChatPromptTemplate.from_messages(
    [
        ("system", "{system_message}"),
        # 대화 기록을 변수로 사용, history 가 MessageHistory 의 key 가 됨
        MessagesPlaceholder(variable_name="history"),
        ("human", "{user_message}"),
    ]
)
runnable = prompt | llm

# 임베딩 설정
embeddings = HuggingFaceEmbeddings(
    model_name="BAAI/bge-m3",
    model_kwargs={'device': 'cpu'},  # 또는 'cuda'로 변경하여 GPU를 사용할 수 있음
    encode_kwargs={'normalize_embeddings': True}  # 임베딩 정규화
)

# Chroma DB설정
collection_name = 'DAIA'
db = Chroma(
    persist_directory='./chroma',
    embedding_function=embeddings,
    collection_name=collection_name
)

# 메모리 히스토리 설정
store = {}  # 빈 딕셔너리를 초기화합니다.

def get_session_history(user_id: str, conversation_id: str) -> BaseChatMessageHistory:
    # 주어진 user_id와 conversation_id에 해당하는 세션 기록을 반환합니다.
    if (user_id, conversation_id) not in store:
        # 해당 키가 store에 없으면 새로운 ChatMessageHistory를 생성하여 저장합니다.
        store[(user_id, conversation_id)] = ChatMessageHistory()
    return store[(user_id, conversation_id)]

with_message_history = RunnableWithMessageHistory(
    runnable,
    get_session_history,
    input_messages_key="user_message",
    history_messages_key="history",
    history_factory_config=[  # 기존의 "session_id" 설정을 대체하게 됩니다.
        ConfigurableFieldSpec(
            id="user_id",  # get_session_history 함수의 첫 번째 인자로 사용됩니다.
            annotation=str,
            name="User ID",
            description="사용자의 고유 식별자입니다.",
            default="",
            is_shared=True,
        ),
        ConfigurableFieldSpec(
            id="conversation_id",  # get_session_history 함수의 두 번째 인자로 사용됩니다.
            annotation=str,
            name="Conversation ID",
            description="대화의 고유 식별자입니다.",
            default="",
            is_shared=True,
        ),
    ],
)


### 2.API
app = FastAPI()
class UserRequest(BaseModel):
    user_message: str

@app.post("/chat")
def generate_answer(req: UserRequest) -> Dict[str, str]:
    context = req.dict()
    user_input = context["user_message"]
    print('>>> 1. 입력값 : ', user_input)

    # 검색
    # retriever = db.as_retriever(search_type="mmr", search_kwargs={'k':3, 'fetch_k': 10})
    retriever = db.as_retriever()
    result = retriever.get_relevant_documents(user_input)
    
    context = ''
    for idx, doc in enumerate(result):
        filename = doc.metadata.get('source', 'unknown') if doc.metadata else 'unknown'
        page = doc.metadata.get('page', 'unknown') if doc.metadata else 'unknown'
        text = doc.page_content

        context += f'\n--- 메타데이터 정보 [파일명: {filename}\n페이지: {page}] ---\n'
        context += f'\n--- 내용 ---\n{text}\n'
    # print('>>> 2. DB 검색 결과 : ', context)

    # LLM 질문
    # 사용자 입력을 받아서 대화를 실행합니다.
    
    # 프롬프트
    system_message = f'''당신은 디에이인포메이션 IA 챗봇. 줄여서 DA봇이야.
    # Instrcution
    1. 친구처럼 반말로 답변해줘
    2. 주어진 검색 결과를 바탕으로 답변해줘
    3. 검색 결과에 관련 내용이 없고, 일상질문이 아니면 답변하지 말아줘.
    4. 인터넷 검색은 하지 말아줘
    5. 검색 결과에 따른 답변일 경우 아래에 검색 결과에 차용된 메타데이터 정보를 출력해줘

    # 검색결과
    {context}
    '''
    # print('>>> 3. 프롬프트 : ', system_message)

    varNow = datetime.datetime.now()
    answer = with_message_history.invoke(
        {
            "user_message": user_input,
            "system_message": system_message,
        },
        config={"configurable": {"user_id": "test1", "conversation_id": "test_conv1"}},
    )

    print('\n\n>>> 4. 최종 답변 : ', answer.content)
    print('\n\n>>> 4. 최종 답변 시간 : ', datetime.datetime.now() - varNow)
    return {"answer": answer.content}
    
    
# FastAPI 서버 실행
import uvicorn
uvicorn.run(app, host="127.0.0.1", port=8000)
```