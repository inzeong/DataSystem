# Housing System

## 1. 프로젝트 소개

#### 🏠  미래 전력 소비량 및 태양광 발전량 예측 및 모니터링 시스템 

21년 기준 건물 부문의 온실가스 배출량이 약70%로 가장 큰 부분을 차지,그 중 전력 에너지의 온실가스 배출량이 제일 많음. <br>
주택의 온실가스 배출량을 감축시키기 위해 인공지능을 활용한 주택 전력 플랫폼 기술을 개발 및 접목한 시스템을 구축. 

<br>

## 2. 구현 기능 
- [x] AMI와 IoT센서를 활용해 실시간 데이터를 수집
- [x] KAFKA & Spark Streaming 사용해 client 대상 실시간 데이터 처리
- [x] HDFS 구축 및 데이터 종류별로 적재
- [x] 기상청 데이터와 연동하여 미래 전력 소비량 및 태양광 발전량을 예측 
- [x] 시계열 데이터 알고리즘 연구 및 모델링 
- [x] 전력소비량과 태양광 발전량을 활용하여 주택의 탄소배출량을 계산
- [x] 관리자,개인,공공기관에 각자 다른 모니터링 서비스를 제공<br>


<br>

## 3. 사용 기술

<img width="700" alt="image" src="https://user-images.githubusercontent.com/43091713/155473726-6dd55caa-31aa-46e9-ac56-82faf50aebce.png">


## 4. 사용 모델

### 전력 생산량 
ARIMA : Auto-regressive Integrated Moving Average  <br>
SARIMA : Seasonal ARIMA  <br> 
LSTM : Long Short Term Memory  <br>

### 태양광 발전량
ARIMA : Auto-regressive Integrated Moving Average  <br>
SARIMA : Seasonal ARIMA  <br>
SARIMA+X : Seasonal ARIMA+X  <br>
LSTM : Long Short Term Memory  <br>
