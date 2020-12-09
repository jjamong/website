---
weight: 1
slug: index
date: 2020-11-03
title: "날짜 선택기(date picker)"
description: "리액트 네이티브(React Native)의 날짜 선택기(date picker) 가이드"
toc: true
---

## 시작하기

### 설치

```
$ yarn add @react-native-community/datetimepicker
```

### 예제

app.js
```
import React, {useState} from 'react';
import {View, Button, Platform} from 'react-native';
import DateTimePicker from '@react-native-community/datetimepicker';

const App: () => React$Node = () => {

  const [date, setDate] = useState(new Date());
  const [mode, setMode] = useState('date');
  const [show, setShow] = useState(false);

  const onChange = (event, selectedDate) => {
    const currentDate = selectedDate || date;
    setShow(Platform.OS === 'ios');
    setDate(currentDate);
    console.log(currentDate)
  };

  const showMode = (currentMode) => {
    setShow(true);
    setMode(currentMode);
  };

  const showDatepicker = () => {
    showMode('date');
  };

  const showTimepicker = () => {
    showMode('time');
  };

  return (
    <View>
        <View>
          <Button onPress={showDatepicker} title="Show date picker!" />
        </View>
        <View>
          <Button onPress={showTimepicker} title="Show time picker!" />
        </View>
        {show && (
          <DateTimePicker
            testID="dateTimePicker"
            value={date}
            mode={mode}
            is24Hour={true}
            display="spinner"
            onChange={onChange}
          />
        )}
    </View>
  );
};

export default App;
```

### 결과

android
![결과_01](/docs/front/react/reactnative/datepicker/01.png)

ios
![결과_02](/docs/front/react/reactnative/datepicker/02.png)