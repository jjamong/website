---
weight: 1
slug: index
date: 2022-07-01
title: "Collection(자료구조)"
description: "Collection(자료구조)"
toc: true
---

## 이해하기

> 보편적으로 ArrayList와 HashMap을 많이 사용합니다.<br>
ArrayList는 DB에서 가져온 List데이터를 순차적으로 출력 하는 방식 등에 자주 사용되고<br>
HashMap은 DB에서 가져온 단일 데이터 또는 Json 형태의 데이터를 출력 하는 방식으로 자주 사용됩니다.

<br>

개발에서 사용되는 많은 데이터들을 메모리 상에서 관리하는 여러 구현 방법을 말합니다.<br>

자료구조의 효율성이 프로그램 성능에 영향을 주기 때문에 적절한 방법을 잘 사용해야 합니다.

<br>

![01](/docs/back/java/java/collection/01.png)

<br>

## List

-- 동일한 데이터 중복을 허용합니다.

-- 데이터 저장 순서가 유지됩니다.

-- 객체를 인덱스로 관리하므로 인덱스가 부여되고 인덱스(객체의 주소값)로 검색, 삭제 할 수 있습니다.

-- List 공통으로 추가, 검색, 삭제 메소드를 갖고 있습니다.

### Arraylist

> 배열에 비해 데이터 관리가(추가, 출력 등) 쉽지만 데이터를 추가하는 과정에서 리소스가 많이 소요 됩니다.<br>
그래도, 관리가 쉬워 사용하기 편리하므로 가장 보편적으로 사용되는 구조 중 하나 입니다.

<br>

```
List<T> list = new Arraylist<T>();

list.add("data");   // 추가
list.get(0);        // 가져오기
list.remove(0);     // 삭제
```

Arraylist는 List 인터페이스의 구현 클래스로 객체를 추가하면 객체가 인덱스로 관리가 됩니다.

다른 언어에서 배열과 같은 기능입니다.<br>
자바에서는 배열의 초기화 시 크기가 고정되어 사용 중에 크기를 변경할 수 없다는 점에서<br>
Array에 비해 Arraylist는 활용 가치가 높아 보편적으로 사용되는 이유 입니다.

그러나, 저장소의 용량을 늘리는 과정에서 기존 Arraylist에 추가하는 것이 아니라<br>
확장된 크기의 Arraylist를 새로 생성하고 기존의 값들을 새로 생성한 공간에 복사하는 과정을 거치면서 많은 리소스가 생길 수 있습니다.

### Vector

> ArrayList와 구조가 같고, Multi Thread(멀티 스레드) 환경에서 동기화가 가능합니다.

<br>

```
Vector<Integer> v = new Stack<>();

v.add(1);
v.add(2);
v.add(3);

System.out.println(v.get(0))
System.out.println(v.get(1))
System.out.println(v.get(2))

// 1
// 2
// 3
```

ArrayList와 동일한 구조이지만, 멀티 쓰레드 환경에서 동기화 문제를 해결합니다.

synchronized를 포함한 메서드로 구현되어 있기 때문에 두 개 이상의 쓰레드에서 같은 메서드를 실행할 때,<br>
하나의 쓰레드에서 사용을 마친 뒤 다른 쓰레드에서 사용할 수 있게 된다.

#### Stack

> 가장 나중에 입력 된 자료가 가장 먼저 출력되는 자료 구조 (Last In First Out/후입선출)

<br>

```
Stack<Integer> stack = new Stack<>();

stack.add(1);
stack.add(2);
stack.add(3);

while (!stack.isEmpty()) {
    System.out.println(stack.pop());
}

// 3
// 2
// 1
```

### LinkedList

<br>

```
LinkedList<Integer> linkedList = new LinkedList<>();

linkedList.add(1);
linkedList.add(2);
linkedList.offer(3);
linkedList.poll();
linkedList.remove();

for (int num : linkedList) {
    System.out.println(num);
}

// 3
```

각 인접하는 노드에 의해 관리 되며, 크기가 동적이고<br>
자바에서는 Queue의 구현에 많이 사용됩니다.

#### Queue

> 가장 먼저 입력 된 자료가 가장 먼저 출력되는 자료 구조 (First In First Out/선입선출)

<br>

```
Queue<Integer> queue = new LinkedList<>();

queue.add(1);
queue.add(2);
queue.offer(3);
queue.poll();
queue.remove();

for (int num : queue) {
    System.out.println(num);
}

// 3
```

## Set

### HashSet

```
HashSet<Integer> hashSet = new HashSet<>();
hashSet.add(0);
hashSet.add(4);
hashSet.add(5);
hashSet.add(5);
hashSet.add(2);
hashSet.add(1);
hashSet.add(3);

Iterator<Integer> it = hashSet.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}


// 0
// 1
// 2
// 3
// 4
// 5
```

```
HashSet<Integer> set = new HashSet<Integer>();//HashSet생성
set.add(1); //값 추가
set.add(2);
set.add(3);
```

## Map

Map은 json 형식과 유사한 키-값(key-value)으로 표현됩니다.

중복된 키값과 순서를 가질 수 없고 값은 중복이 가능합니다.



### HashMap

> 다른(HashTable, TreeMap) Map과 다르게 NULL값이 허용되고 가장 보편적으로 사용되는 Map방식 입니다.

<br>

```
Map<String, Integer> map = new HashMap<>();

fruits.put("1", 1);
fruits.put("2", 2);
fruits.put(null, 3);
fruits.put("4", null);
fruits.put("5", 5);

System.out.println(map.get("1"))
System.out.println(map.get("2"))
System.out.println(map.get(null))
System.out.println(map.get(4))
System.out.println(map.get("5"))

// 1
// 2
// 3
// null
// 5
```