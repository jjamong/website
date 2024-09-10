---
weight: 1
slug: index
date: 2024-09-10
title: "메모리 - 대화내용 기억"
description: "메모리 - 대화내용 기억"
toc: true
---

## 시작하기

### ConversationBufferMemory

이 메모리는 메시지를 저장한 다음 변수에 메시지를 추출할 수 있게 해줍니다.

```
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain.chains import ConversationChain
from langchain.memory import ConversationBufferMemory

# llm 설정
llm = ChatGoogleGenerativeAI(
    model="gemini-pro",
    google_api_key="AIzaSyDGuiujLz0sb927K6p8NoRzUxFEdVmHnDc",
)

memory = ConversationBufferMemory()
conversation = ConversationChain(
    llm=llm, 
    memory = memory
)

memory.save_context(
    {"input": "너의 이름은 뭐야?"}, 
    {"output": "에이전트 B 입니다."}
)

conversation.predict(input="안녕, 난 에이전트 A 라고 해.")

conversation.predict(input="너의 이름이 뭐지?")

conversation.predict(input="내 이름이 뭐였지?")

print(memory.buffer)
# memory.load_memory_variables({})

##########################################
# Human: 너의 이름은 뭐야?
# AI: 에이전트 B 입니다.
# Human: 안녕, 난 에이전트 A 라고 해.
# AI: 반갑습니다, 에이전트 A님. 저는 에이전트 B입니다. 오늘 저와 대화를 나누어 주셔서 감사합니다.
# Human: 너의 이름이 뭐지?
# AI: 저는 에이전트 B입니다.
# Human: 내 이름이 뭐였지?
# AI: 에이전트 A입니다.
```


### ConversationTokenBufferMemory

ConversationTokenBufferMemory 는 최근 대화의 히스토리를 버퍼를 메모리에 보관하고, 대화의 개수가 아닌 토큰 길이 를 사용하여 대화내용을 플러시(flush)할 시기를 결정합니다.

```
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain.chains import ConversationChain
from langchain.memory import ConversationTokenBufferMemory

# llm 설정
llm = ChatGoogleGenerativeAI(
    model="gemini-pro",
    google_api_key="AIzaSyDGuiujLz0sb927K6p8NoRzUxFEdVmHnDc",
)

memory = ConversationTokenBufferMemory(llm=llm, max_token_limit=200)
conversation = ConversationChain(
    llm=llm, 
    memory = memory
)

memory.save_context(
    {"input": "너의 이름은 뭐야?"}, 
    {"output": "에이전트 B 입니다."}
)

conversation.predict(input="안녕, 난 에이전트 A 라고 해.")

conversation.predict(input="너의 이름이 뭐지?")

conversation.predict(input="내 이름이 뭐였지?")

print(memory.buffer)
# memory.load_memory_variables({})

##########################################
# Human: 너의 이름은 뭐야?
# AI: 에이전트 B 입니다.
# Human: 안녕, 난 에이전트 A 라고 해.
# AI: 안녕하세요, 에이전트 A. 저는 에이전트 B입니다. 만나서 반갑습니다.
# Human: 너의 이름이 뭐지?
# AI: 에이전트 B입니다.
# Human: 내 이름이 뭐였지?
# AI: 에이전트 A입니다.


##########################################
max_token_limit=80 으로 했을 경우

# AI: 반갑습니다, 에이전트 A. 어떻게 도와드릴까요?
# Human: 너의 이름이 뭐지?
# AI: 에이전트 B입니다.
# Human: 내 이름이 뭐였지?
# AI: 죄송합니다. 귀하의 이름이 무엇인지 모릅니다.
```


### ConversationSummaryBufferMemory

ConversationSummaryBufferMemory 는 두 가지 아이디어를 결합한 것입니다.

최근 대화내용의 버퍼를 메모리에 유지하되, 이전 대화내용을 완전히 플러시(flush)하지 않고 요약으로 컴파일하여 두 가지를 모두 사용합니다.

```
from langchain_google_genai import ChatGoogleGenerativeAI
from langchain.chains import ConversationChain
from langchain.memory import ConversationSummaryBufferMemory

# llm 설정
llm = ChatGoogleGenerativeAI(
    model="gemini-pro",
    google_api_key="AIzaSyDGuiujLz0sb927K6p8NoRzUxFEdVmHnDc",
)

memory = ConversationSummaryBufferMemory(llm=llm, max_token_limit=2000)
conversation = ConversationChain(
    llm=llm, 
    memory = memory
)

memory.save_context(
    {"input": "너의 이름은 뭐야?"}, 
    {"output": "에이전트 B 입니다."}
)

schedule = "내일 아침 8시에 에이전트 C와 미팅이 있습니다. \
1시에는 에이전트 Z와 식사가 있습니다. \
3시에는 에이전트 D와 행사가 있습니다."
memory.save_context({"input": "내일 일정에 대해 알려줘."}, 
                    {"output": f"{schedule}"})

conversation.predict(input="내일 3시 행사 다음 5시에 에이전트 E와 미팅 일정 추가해줘")

conversation.predict(input="내일 5시에 누구랑 미팅이었지?")
print(memory.buffer)
print('>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')

conversation.predict(input="내일 3시 행사 참석 인원은 1000명 정도야. 인원정보도 추가해줘")

conversation.predict(input="내일 5시에 누구랑 미팅이고, 3시에 참석 인원이 몇명이었지?")
print(memory.buffer)

##########################################
# Human: 너의 이름은 뭐야?
# AI: 에이전트 B 입니다.
# Human: 내일 일정에 대해 알려줘.
# AI: 내일 아침 8시에 에이전트 C와 미팅이 있습니다. 1시에는 에이전트 Z와 식사가 있습니다. 3시에는 # 에이전트 D와 행사가 있습니다.
# Human: 내일 3시 행사 다음 5시에 에이전트 E와 미팅 일정 추가해줘
# AI: 일정에 추가했습니다.
# Human: 내일 5시에 누구랑 미팅이었지?
# AI: 에이전트 E
# >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
# Human: 너의 이름은 뭐야?
# AI: 에이전트 B 입니다.
# Human: 내일 일정에 대해 알려줘.
# AI: 내일 아침 8시에 에이전트 C와 미팅이 있습니다. 1시에는 에이전트 Z와 식사가 있습니다. 3시에는 # 에이전트 D와 행사가 있습니다.
# Human: 내일 3시 행사 다음 5시에 에이전트 E와 미팅 일정 추가해줘
# AI: 일정에 추가했습니다.
# Human: 내일 5시에 누구랑 미팅이었지?
# AI: 에이전트 E
# Human: 내일 3시 행사 참석 인원은 1000명 정도야. 인원정보도 추가해줘
# AI: 일정에 인원정보를 추가했습니다.
# Human: 내일 5시에 누구랑 미팅이고, 3시에 참석 인원이 몇명이었지?
# AI: 내일 5시에 에이전트 E와 미팅이며, 3시 행사 참석 인원은 1000명입니다.
```