---
weight: 1
slug: index
date: 2022-07-12
title: "Procedure(프로시저)"
description: "Procedure(프로시저)"
toc: true
---

## Stored Procedure(저장 프로시저)

SQL문장을 선언해 MySQL에 저장하고, 해당 SQL문을 함수처럼 사용하는 것으로<br>
생성한 프로시저를 함수처럼 호출하여 편하게 사용할 수 있습니다.

### 생성
```
DELIMITER $$

DROP PROCEDURE IF EXISTS UPDATE_LOOP $$

CREATE PROCEDURE UPDATE_LOOP()

BEGIN

	declare I int;
	declear T int;
	set I = 1;
	set T = 10000;

	while T <= 1000000 DO
		update table set a = 1
		where seq between I and T;

		set I = I + 10000;
		set T = T + 10000;

	END while;
END $$

DELIMITER ;
```

### 호출
```
CALL UPDATE_LOOP();
```

### DELIMITER 

저장 프로시저 내부에 사용하는 SQL문은 일반 SQL문이기때문에 세미콜론(;)으로 문장을 끝맺어야 합니다.<br>
저장 프로시저 작성이 완료되지 않았음에도 SQL문이 실행되는 위험을 막기 위해 구분자(;)를 다른 구분자로 바꿔주어야하는데 이 때 사용하는 명령어가 DELIMITER 입나다.

따라서 저장 프로시저 생성 전에 구분자(DELIMITER)를 $$ 으로 바꿔주고 프로시저 작성이 끝났을 때 END $$ 로 저장 프로시저의 끝을 알려준다.<br>
마지막으로 구분자를 원래대로 되돌리기 위해 구분자(DELIMITER)를 세미콜론(;)으로 바꿔준다.