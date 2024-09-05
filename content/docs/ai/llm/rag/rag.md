---
weight: 1
slug: index
date: 2024-09-05
title: "RAG(Retrieva Augmented Generation)"
description: "RAG(Retrieva Augmented Generation)"
toc: true
---

## 이해하기



## 시작하기


```
%%time

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
collection_name = 'chromaTest'
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
            # print('>>> pdf', file)
            loader = PyMuPDFLoader(file_path)

            
        if file.endswith('.docx') or file.endswith('.doc') :
            # print('>>> docx', file)
            loader = Docx2txtLoader(file_path)

            
        if file.endswith('.txt') :
            # print('>>> docx', file)
            loader = TextLoader(file_path)

            
        if file.endswith('.xlsx') or file.endswith('.xls') :
            # print('>>> docx', file)
            loader = UnstructuredExcelLoader(file_path, mode="elements")

        pages = loader.load()
        text_splitter = RecursiveCharacterTextSplitter(chunk_size=500, chunk_overlap=50)
        texts = text_splitter.split_documents(pages)

        # 텍스트와 임베딩 확인
        print(f"텍스트 수 : {len(texts)}")
        if len(texts) > 0 :
            ''''''
            # 컬렉션 이름
            # collection_name = 'test_' + str(len(texts))

            # Chroma 데이터베이스 생성
            db.from_documents(
                texts,
                embeddings,
                persist_directory='./chroma',
            )

    except Exception as e:
        print(f"Error {file} : {e}")

```

```
import os
from dotenv import load_dotenv

from langchain.vectorstores import Chroma
from langchain_community.embeddings import HuggingFaceEmbeddings

load_dotenv()

# 임베딩 설정
embeddings = HuggingFaceEmbeddings(
    model_name="BAAI/bge-m3",
    model_kwargs={'device': 'cpu'},  # 또는 'cuda'로 변경하여 GPU를 사용할 수 있음
    encode_kwargs={'normalize_embeddings': True}  # 임베딩 정규화
)

# 컬렉션 이름
collection_name = 'langchain'

# Chroma 클라이언트 초기화
db = Chroma(
    persist_directory='./chroma',
    embedding_function=embeddings,
    collection_name=collection_name
)

# similarity_search 검색 (가장 유사한 벡터 검색)
query = "이상미의 직업은?"
result1 = db.similarity_search(query, k=3)
# result = db.get()
print('>>> result1', result1)

# 검색 결과를 저장할 데이터프레임 생성
results_list = []
for doc in result1:
    results_list = {
        'filename': doc.metadata.get('source', 'unknown'),
        'page': doc.metadata.get('page', 'unknown'),
        'text': doc.page_content
    }
    print('>>> filename : ', results_list['filename'])
    print('>>> page : ', results_list['page'])
    print('>>> text : ', results_list['text'])

# as_retriever 검색
# 벡터 저장소를 기반으로 벡터스토어리트리버를 생성합니다.
retriever = db.as_retriever(search_type="mmr", search_kwargs={"k": 3, "fetch_k": 10})

# 검색
query = "이상미의 직업은?"
result2 = retriever.get_relevant_documents(query)
print('>>> result2', result2)

# 결과 출력
for idx, doc in enumerate(result2):
    # print(f"Result {idx + 1}:")
    print(f"  filename: {doc.metadata.get('source', 'unknown')}")
    print(f"  Page: {doc.metadata.get('page', 'unknown')}")
    print(f"  Text: {doc.page_content}")
    print()

```