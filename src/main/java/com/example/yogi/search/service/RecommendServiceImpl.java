package com.example.yogi.search.service;

import com.example.yogi.member.dto.MemberResponse;
import com.example.yogi.member.entity.Member;
import com.example.yogi.member.repository.MemberRepository;
import com.example.yogi.search.dto.DestinationResponse;
import com.example.yogi.search.dto.RecommendResponse;
import com.example.yogi.search.entity.Destination;
import com.example.yogi.search.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService{
    private final DestinationRepository destinationRepository;
    private final MemberRepository memberRepository;

    @Override
    public RecommendResponse getRecommendList(String id) {
        RecommendResponse response=new RecommendResponse();

        if(null==id || "".equals(id)){
            response.setMode(RecommendResponse.Mode.NOT_LOGIN);
            return response;
        }

        //사용자 관심 여행지 취득
        List<String> userLikeList = memberRepository.findById(id)
                .map(member -> Arrays.stream(member.getUserlike().split(",")))
                .orElseGet(Stream::empty)
                .map(String::trim)
                .toList();

        if(userLikeList.size()==0){
            response.setMode(RecommendResponse.Mode.EMPTY_FAVORITE);
            return response;
        }

        response.setMode(RecommendResponse.Mode.HAS_RECOMMEND);

        //TODO
        List<DestinationResponse> recommendList = new ArrayList<>();

//        destinationRepository.findById();
        return response;
    }

    //추천 리스트 생성 로직
//    //param 사용자ID, 사용자 관심여행지 Id 리스트
//    private List<DestinationResponse> getRecList(String loginID, List<String> likeList){
//        Vector<Destination> destList = new Vector<>();
//        Vector<Member> memberList = new Vector<>();
//
//        for (String destId : likeList){
//            destList.add(destinationRepository.findById(destId).map(Destination::new).orElseThrow());
//        }
//
//        // target id
//        String targetid = loginID;
//        // target rating Matrix
//        Map<String, Double> target = new HashMap<>(); //target userid, target ratingMatrix {"센소지"=1.0, "후지산=1.0...}
//        // 평가 행렬
//        Map<String, Map> ratingMatrix = new HashMap<>();// 비교 사용자id, 비교 사용자 ratingMatrix
//
//        // target의 찜한 여행지를 Map으로 불러오기(찜한 여행지는 무조건 1점)
//        for (Member member : memberList) {
//
//            //target user라면 targetmap에 여행지와 여행지 점수를 담는다.(점수는 무조건 1점)
//            if (member.getId().equals(targetid)) {
//                String[] targetlikelist = member.getUserlike().split(",");
//                for (int i = 0; i < targetlikelist.length; i++) {
//                    target.put(targetlikelist[i], 1.0);
//                }
//            } else { //target user가 아니라면(유사한 사용자 후보)
//                Map<String, Double> candiuser = new HashMap<>(); //각 유저별로 찜한 목록과 점수 저장 {"센소지"=1.0, "후지산=1.0...}
//                if(member.getUserlike()!=null) { //좋아요 한 리스트가 있는 유저라면
//                    String[] targetlikelist = dest.getUserlike().split(","); //"센소지, 후지산..." ->["센소지", "후지산"...]
//                    for (int i = 0; i < targetlikelist.length; i++) {
//                        candiuser.put(targetlikelist[i], 1.0); //{"센소지"=1.0, "후지산=1.0...}
//                    }
//                    ratingMatrix.put(dest.getId(), candiuser); //{"userid02"={"센소지"=1.0, "후지산=1.0...}, ...}
//                }
//            }
//        }
//
//        // user1에 대한 유사도 행렬
//        Map<String, Double> simMatrix = new HashMap<>();
//
//        // 1*n 행렬(target*comparison)
//        for (String userid : ratingMatrix.keySet()) {
//            double score = cosineSimilarity(target, ratingMatrix.get(userid));
//            // 점수 오름차순 정렬을 위해 점수+id로 저장
//            simMatrix.put(score + " " + userid, score); //{비교user= target과 비교user과의 cosine similarity}
//        }
//
//        // sort(오름차순 정렬)
//        List<String> similarUsers = new ArrayList<>(simMatrix.keySet()); //key 기준으로 정렬
//        Collections.sort(similarUsers,Collections.reverseOrder()); //유사한 사용자를 내림차순으로 정렬
//
//        //여기에 상위 몇명의 similarUsers까지 사용할지 슬라이싱 하는 코드 작성
//        if(similarUsers.size()>5) {
//            similarUsers=similarUsers.subList(0, 5);
//        }
//
//        //많이 나온 여행지 개수 세기
//        Map<String, Integer> destlist=new HashMap<>();
//        for (String string : similarUsers) {
//            String userid=string.split(" ")[1]; //정렬된 similarUser에서 userid맨 빼오기
//            for(Object key : ratingMatrix.get(userid).keySet()) {
//                if(!key.equals("")) {
//                    try{
//                        //키가 이미 destlist에 있을때
//                        destlist.put((String)key, destlist.get(key)+1);
//                    }catch(Exception e) {
//                        //키가 destlist에 없을때 새로 넣어줌(count=1부터)
//                        destlist.put((String)key, 1);
//                    }
//                }
//            }
//
//        }
//
//        //destlist의 [frequency+여행지 이름] 문자열로 합쳐진 배열을 만들어줌(정렬을 위해서)
//        List<String> sortedList=new ArrayList<>();
//
//        for (String string : destlist.keySet()) {
//            sortedList.add(destlist.get(string)+","+string); // ,로 구분
//        }
//
//        //후보지 내림차순 정렬
//        Collections.sort(sortedList,Collections.reverseOrder());
//
//        ArrayList<String> keywords=new ArrayList<>();
//
//        for (String string:sortedList) {
//            System.out.println(string.split(",")[1]+":"+string.split(",")[0]);
//            keywords.add(string.split(",")[1]);
//        }
//
//
//        //후보지 추천 Vector 가져오기
//        DestDAO ddao=DestDAO.getInstance();
//        Vector<DestVO> volist=ddao.getRecommandList(keywords);
//
//        return volist
}
