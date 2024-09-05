---
weight: 1
slug: index
date: 2024-09-05
title: "LLM(Large Language Model)"
description: "LLM(Large Language Model)"
toc: true
---

## 이해하기

대규모 언어 모델(LLM)은 텍스트를 인식하고 생성하는 등의 작업을 수행할 수 있는 일종의 인공 지능(AI) 프로그램입니다. LLM은 방대한 데이터 세트를 학습하므로 "대규모"라는 이름이 붙었습니다. LLM은 머신 러닝, 특히 트랜스포머 모델이라고 하는 일종의 신경망을 기반으로 합니다.


## 시작하기

https://ollama.com/download
https://ollama.com/library/llama3.1

Ollama 설치, llama3.1 모델 설치

```
from langchain_community.chat_models import ChatOllama

llm = ChatOllama(model='llama3.1:latest')
result = llm.invoke('안녕')

print(result)
// content='안녕하세요! 어떻게 지내세요?' response_metadata={'model': 'llama3.1:latest', 'created_at': '2024-09-05T01:40:56.8496289Z', 'message': {'role': 'assistant', 'content': ''}, 'done_reason': 'stop', 'done': True, 'total_duration': 35636020800, 'load_duration': 18777458800, 'prompt_eval_count': 13, 'prompt_eval_duration': 11959563000, 'eval_count': 9, 'eval_duration': 4826815000} id='run-92e77a59-fe88-42a3-a9bb-0044e1305deb-0'
```