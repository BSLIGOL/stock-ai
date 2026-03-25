-- 💡 [사수의 보너스 템플릿] 초기 데이터베이스 스키마 및 pgvector 셋팅 스크립트
-- 이 쿼리 파일 한 장이면 친구 컴퓨터도 10초 만에 동일한 DB 세팅이 가능합니다.

-- 1. pgvector 활성화 (매우 중요!)
CREATE EXTENSION IF NOT EXISTS vector;

-- 2. 자산군(Asset) 마스터 정보 테이블 (주식, 채권, 선물 확장 가능)
CREATE TABLE IF NOT EXISTS stocks (
    id SERIAL PRIMARY KEY,
    asset_type VARCHAR(20) NOT NULL DEFAULT 'STOCK', -- STOCK, BOND, FUTURES
    ticker_code VARCHAR(10) UNIQUE NOT NULL,         -- 종목코드 (예: 005930)
    name VARCHAR(100) NOT NULL,                      -- 종목명 (예: 삼성전자)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. 실시간 뉴스 및 공시 본문 저장 테이블
CREATE TABLE IF NOT EXISTS announcements (
    id SERIAL PRIMARY KEY,
    stock_id INTEGER REFERENCES stocks(id) ON DELETE CASCADE,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,                           -- 기사/공시 전체 본문
    summary TEXT,                                   -- AI 요약본
    sentiment_score VARCHAR(20),                     -- POSITIVE, NEGATIVE, NEUTRAL
    source VARCHAR(50) NOT NULL,                    -- NAVER_NEWS, DART 등
    published_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. RAG 및 노이즈 필터를 위한 임베딩(Vector) 저장 테이블
CREATE TABLE IF NOT EXISTS announcement_embeddings (
    id SERIAL PRIMARY KEY,
    announcement_id INTEGER REFERENCES announcements(id) ON DELETE CASCADE,
    embedding vector(768) NOT NULL,                  -- Gemini 2.0 Embedding dimension
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. 벡터 검색 성능 향상을 위한 인덱스 생성
CREATE INDEX IF NOT EXISTS announcement_embeddings_index 
ON announcement_embeddings USING ivfflat (embedding vector_cosine_ops);
