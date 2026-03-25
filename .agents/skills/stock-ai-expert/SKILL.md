---
name: stock-ai-expert
description: Stock-AI 프로젝트에 특화된 지식 저장소 (ERD, API 규격, 아키텍처 가이드)
---

# 💹 Stock-AI Expert Skill

이 스킬은 Stock-AI 프로젝트의 핵심 컨텍스트를 제공하여, AI가 프로젝트 전문가로서 답변할 수 있도록 돕습니다.

## 🏗️ Project Architecture
- **구조**: **도메인 아키텍처 (Domain Architecture / Package by Feature)**
- **특징**: 도메인(뉴스, 주식, 분석 등)을 기준으로 패키지를 나누어 관리하며, 각 도메인 내부에 `controller`, `service`, `repository`, `entity`, `dto`를 포함합니다.
- **주요 도메인**: `news`, `stock`, `analysis`, `common`

## 📊 Database Schema (ERD)
- **`stocks`**: 종목 마스터 정보 (ticker_code, name)
- **`announcements`**: 뉴스/공시 본문 및 AI 요약, 감성 점수
- **`announcement_embeddings`**: `vector(768)` 타입을 사용한 뉴스 본문 임베딩 데이터
- **관계**: `stocks` (1) <-> (N) `announcements` (1) <-> (1) `announcement_embeddings`

## 🤖 AI Implementation (Models from application.properties)
- **Summary Model**: `gemini-3.1-flash-lite-preview`
- **Deep Analysis Model**: `gemini-2.5-pro`
- **Embedding Model**: `gemini-embedding-2-preview`
- **Vector Search**: PostgreSQL `pgvector` 활용 (코사인 유사도 기반)
- **최적화**: 뉴스 요약 시 3줄 이내로 제한, 감성 분석 결과(POSITIVE/NEGATIVE) 동반 필수.

## 🛠️ Specialized Helpers
- **JPA Helper**: `QueryDSL` 대신 `Spring Data JPA` 기본 메서드와 `@Query` 활용 권장.
- **RAG Helper**: 검색 결과가 부족할 경우 `google-search-mcp`를 통한 보완 로직 제안.
