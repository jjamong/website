---
weight: 1
slug: index
date: 2024-09-05
title: "프롬프트"
description: "프롬프트"
toc: true
---

## 이해하기

**프롬프트(Prompt)**는  **AI에게 주는 질문, 명령, 요청문**이라고 생각하면 됩니다.

사람이 “이렇게 해줘”라고 말을 걸면  
AI는 그 말을 이해하고, 거기에 맞는 답을 만들어냅니다.  
그 시작점이 되는 입력 문장이 바로 프롬프트입니다.

💡

> ✏️ 프롬프트: “프랑스 파리에 대해 3줄로 요약해줘”  
> 🤖 AI 응답:  
> - 파리는 프랑스의 수도이며 유럽의 문화 중심지입니다.  
> - 에펠탑, 루브르 박물관 같은 랜드마크가 있습니다.  
> - 음식, 패션, 예술로도 전 세계적으로 유명합니다.

### 🔧 프롬프트가 중요한 이유

프롬프트를 어떻게 작성하느냐에 따라  
AI의 답변 정확도와 품질이 크게 달라집니다.

| 좋은 프롬프트 | 그렇지 않은 프롬프트 |
|---------------|------------------------|
| “3줄 요약해줘” | “파리?” |
| “비즈니스 이메일 형식으로 다시 써줘” | “다시 써봐” |
| “이 코드에 주석을 추가해줘” | “이거 고쳐줘” |

프롬프트가 구체적이고 명확할수록  
AI가 보다 정확하고 유용한 답변을 제공합니다.

### 📘 효과적인 프롬프트 작성법

- 🎯 **구체적인 요청을 합니다**  
  → “요약해줘”보다는 “3문장으로 요약해줘”가 더 효과적입니다.

- 🧠 **AI의 역할을 설정합니다**  
  → “여행 전문가라고 가정하고, 파리에 대해 설명해주세요”

- 🗂 **출력 형식을 지정합니다**  
  → “목차, 설명, 예시 순으로 정리해주세요”

이러한 방식으로 프롬프트를 작성하면  
보다 원하는 방향의 결과를 얻을 수 있습니다.

### ✍️ 프롬프트는 하나의 기술로 발전하고 있습니다

최근에는 프롬프트를 단순히 입력하는 수준을 넘어,  
**전략적으로 설계하는 기술인 ‘프롬프트 엔지니어링(Prompt Engineering)’**이 주목받고 있습니다.

이를 통해 다음과 같은 작업들이 가능해집니다.

- 챗봇의 응답 품질 개선
- 복잡한 코드 자동화
- 문서 요약 및 분류
- 의미 기반 검색 정밀화

AI가 주어진 정보를 어떻게 처리할지 결정짓는  
가장 핵심적인 단서가 바로 프롬프트입니다.

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