---
weight: 1
slug: index
date: 2024-09-06
title: "Loader(로더)"
description: "Loader(로더)"
toc: true
---

## 이해하기

파일 로더의 핵심은 아래와 같습니다.

-- 텍스트를 원형 그대로 가져오는가?<br>
-- 메타데이터는 어떤 것들이 있는가?<br>
-- 문서를 읽어 오는 속도는 얼마나 빠른가?


## 파일 로더

```
import os
from langchain_community.document_loaders import PyMuPDFLoader, Docx2txtLoader, TextLoader, UnstructuredExcelLoader

files = [file for file in os.listdir('./dataset')]
for file in files:
    file_path = os.path.join('./dataset', file)

    try:
        # 로더 선택
        if file.endswith('.pdf'):
            loader = PyMuPDFLoader(file_path)
        elif file.endswith('.docx') or file.endswith('.doc'):
            loader = Docx2txtLoader(file_path)
        elif file.endswith('.txt'):
            loader = TextLoader(file_path)
        elif file.endswith('.xlsx') or file.endswith('.xls'):
            loader = UnstructuredExcelLoader(file_path, mode="elements")
        else:
            print(f"Unsupported file type: {file}")
            continue

        # 파일 데이터 로드
        filedata = loader.load()
        
        # 파일 데이터 출력
        if isinstance(filedata, list):
            for i, data in enumerate(filedata):
                print(f"--- Data chunk {i} ---")
                print(data)
                print("\n" + "="*50 + "\n")
        else:
            print(filedata)
        
    except Exception as e:
        print(f"Error processing file {file}: {e}")
```