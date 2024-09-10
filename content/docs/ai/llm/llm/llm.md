---
weight: 1
slug: index
date: 2024-09-10
title: "LLM(Large Language Model)"
description: "LLM(Large Language Model)"
toc: true
---

## 이해하기

대규모 언어 모델(LLM)은 텍스트를 인식하고 생성하는 등의 작업을 수행할 수 있는 일종의 인공 지능(AI) 프로그램입니다.

LLM은 머신 러닝, 특히 트랜스포머 모델이라고 하는 일종의 신경망을 기반으로 합니다.



### LLM 모델 경험
llm모델을 PC에 설치해서 사용해 본 결과, 
8B(마라미터 약 80억개) 답변은 적당히 상식선에 답변이였으나,
컴퓨터 성능의 이슈로 간단한 인사말 답변도 1분이 넘었고, RAG등이나 프롬프트를 추가하면 10분도 훨씬 넘게 되었습니다.

2B인 SLLM 제미니도 로컬 설치해서 사용했을 경우엔 답변 속도 자체는 조금 빨라졌으나(일상답변 10몇초, 프롬프트 추가 3분 이상) 답변이 적절치 않거나, 상식을 넘은 할루시네이션 등이 많이 발생해 사용 불가할 정도로 판단하게 되었습니다.

제미니 API를 등록해서 사용해본 결과 속도는 로컬 2B보다 훨씬 빠른 수준에
답변은 8B이상의 수준으로 답변을 해주는 것 같습니다.

## 시작하기


### Gemini (구글)

API 키 등록 : https://aistudio.google.com/app/apikey


```
import google.generativeai as genai

# llm 설정
genai.configure(
    api_key=GOOGLE_API_KEY
)
model = genai.GenerativeModel('gemini-pro')

response = model.generate_content("한국에 대해서 말해줘")
print(response.text)
##############################################
**대한민국**

**지리:**

* 동아시아 한반도 남부에 위치
* 북쪽으로 조선민주주의인민공화국(북한)과 국경을 맞닿음
* 동쪽으로 동해, 서쪽으로...
```

#### ChatGoogleGenerativeAI
```
from langchain_google_genai import ChatGoogleGenerativeAI

# llm 설정
llm = ChatGoogleGenerativeAI(
    model="gemini-pro",
    google_api_key=GOOGLE_API_KEY
)

response = model.generate_content("한국에 대해서 말해줘")
print(response.text)
```


### Lama (메타)

https://ollama.com/download
https://ollama.com/library/llama3.1

Ollama 설치, llama3.1 모델 설치

```
from langchain_community.chat_models import ChatOllama

llm = ChatOllama(model='llama3.1:latest')
result = llm.invoke('안녕')

print(result)
##############################################
content='안녕하세요! 어떻게 지내세요?' response_metadata={'model': 'llama3.1:latest', 'created_at': '2024-09-05T01:40:56.8496289Z', 'message': {'role': 'assistant', 'content': ''}, 'done_reason': 'stop', 'done': True, 'total_duration': 35636020800, 'load_duration': 18777458800, 'prompt_eval_count': 13, 'prompt_eval_duration': 11959563000, 'eval_count': 9, 'eval_duration': 4826815000} id='run-92e77a59-fe88-42a3-a9bb-0044e1305deb-0'
```