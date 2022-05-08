# 2022-05-09
# 백만장자 프로젝트

T = int(input()) # 테스트케이스 수

for test_case in range(1, T+1):
    N = int(input())

    price = list(map(int,input().split())) # 일일 매매가를 입력한다
    profit = 0 # 이익값

    maxValue = price[-1] # Max를 리스트 제일 뒤 요소로 잡는다

    for i in range(N-2, -1, -1): 
        if maxValue < price[i]: maxValue = price[i] # Max값보다 크면 Max값 재지정
        else: profit += maxValue - price[i] # Max값보다 작으면 이익계산 후 합산

    print('#%d %d' %(test_case, profit)
    
# 어려웠던 이유 : 뒤에서 계산한다는 생각을 못함.
#                왼쪽에서 오른쪽으로 계산하는 편견.
                 case1: 매매최대값이 앞에 있는 경우
                 case2: 매매최대값이 가운데에 있는 경우
                 case3: 매매최대값이 뒤에 있는 경우
                 왼쪽에서 계산할 경우 매매최대값이 중간에 있다면 결국에는 이익 계산 후 그 다음 최대값을 찾아서 계산해야 하지만
                 역계산을 하면 매매최대값을 작은 순에서 큰 순으로 계산하게 되므로 어려운 계산이 아니었다.
