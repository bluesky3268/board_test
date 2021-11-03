package com.example.demo.util;

import java.util.List;

import com.example.demo.entity.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Pager {
    private int totalCount; // 전체 데이터 수
    private int pageNum; // 현재 페이지 번호
    private int dataNum; // 한 페이지에 표시할 데이터 수
    private int startPage = 1; // 현재 페이지 블럭의 시작페이지
    private int endPage = 5; // 현재 페이지 블럭의 마지막 페이지
    private boolean next;
    private boolean prev;
    private int currentBlock; // 현재 페이지 블럭
    private int lastBlock; // 마지막 페이지 블럭

//    private List<Board> noticeList;

    public Pager(int totalCount, int pageNum, int dataNum) {
    	this.totalCount = totalCount;
    	this.pageNum = pageNum;
    	this.dataNum = dataNum;
    }
    
    // 총 페이지 수 계산
    public int calPage(int totalCount, int dataNum) {
    	// 나눈 값이 소수점이 나오는 경우 +1을 해줘야 함 : 12.6 일 경우 13페이지가 되야 모든 데이터를 출력 가능
        int totalPage = totalCount / dataNum;
        if (totalPage % dataNum > 0) {
            totalPage++;
        }
        return totalPage;
    }


    public void checkNextPrev(int pageNum) {
        // 페이지 수가 5보다 작으면 이전, 다음 필요없음
        if (calPage(totalCount, dataNum) < 5) {
            setPrev(false);
            setNext(false);
        }
        // 현재 페이지가 1 ~ 5 사이일 경우 이전 버튼 필요없음
        else if (pageNum > 0 && pageNum < 6) {
            setPrev(false);
            setNext(true);
        }
        // 현재 블럭(11 ~ 15)이 마지막 블럭(11 ~ 15)과 같으면 다음 버튼 필요없음
        else if (getLastBlock() == getCurrentBlock()) {
            setPrev(true);
            setNext(false);
        } else{
            setPrev(true);
            setNext(true);
        }
    }

    public void setStartPage(int currentBlock) {
        // 현재 블럭이 2번째 블럭일 경우 -> 시작 페이지는 6 마지막페이지는 10
        this.startPage = (currentBlock * 5) + 1;
    }

    public void setEndPage(int getLastBlock, int getCurrentBlock) {
        // 현재 블럭이 2번째 블럭일 경우 -> 시작 페이지는 6 마지막페이지는 10
        if (getLastBlock != getCurrentBlock) {
            this.endPage = getStartPage() + 4;
        }else{
            // 현재 블럭이 마지막 블럭일 경우 총 페이지수 == 마지막 페이지
            this.endPage = calPage(getTotalCount(), getDataNum());
        }
    }

    public void setCurrentBlock(int pageNum) {
        // 현재 페이지를 통해서 몇번째 블럭인지 구하기
        // 현재 페이지가 17 -> 1 - 5, 6 - 10, 11 - 15, 16 - 20 -> 4번째 블럭
        this.currentBlock = pageNum / 5;
        // 16, 17, 18, 19 -> 4번째 블럭 -> 나머지가 있을 경우 1을 더해줘야 함
        if (pageNum % 5 > 0) {
            this.currentBlock++;
        }
    }

    public void setLastBlock(int lastBlock) {
        this.lastBlock = totalCount / (5 * this.dataNum);
        if (totalCount % (5 * this.dataNum) > 0) {
            this.lastBlock++;
        }
    }
}