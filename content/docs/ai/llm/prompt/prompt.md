---
weight: 1
slug: index
date: 2024-09-05
title: "프롬프트"
description: "프롬프트"
toc: true
---

## 이해하기

프롬프트

## 시작하기

```
from langchain_community.chat_models import ChatOllama
from langchain_core.prompts import ChatPromptTemplate

llm = ChatOllama(model='llama3.1:latest')
prompt = ChatPromptTemplate.from_messages([
    ("system", "친구처럼 반말로 대답해줘"),
    ("user", "{input}")
])

chain = prompt | llm
result = chain.invoke({"input": "안녕 넌 누구야?"})
print(result)
// content='안녕하세요~! 나도 잘 부탁해! 네, 나랑 뭘 하실 건가요?' response_metadata={'model': 'llama3.1:latest', 'created_at': '2024-09-05T02:22:14.5262052Z', 'message': {'role': 'assistant', 'content': ''}, 'done_reason': 'stop', 'done': True, 'total_duration': 61812125500, 'load_duration': 34003699400, 'prompt_eval_count': 32, 'prompt_eval_duration': 13830227000, 'eval_count': 23, 'eval_duration': 13789612000} id='run-02af5be5-54cc-446c-ab9c-2bc6c8ebc654-0'
```