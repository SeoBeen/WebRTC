package com.ssafy.db.repository;

import com.ssafy.api.response.ConsultantListRes;
import com.ssafy.db.entity.ConsultantProfile;
import com.ssafy.db.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface ConsultantRepository extends JpaRepository<ConsultantProfile, Long> {
    // 아래와 같이, Query Method 인터페이스(반환값, 메소드명, 인자) 정의를 하면 자동으로 Query Method 구현됨.
    @Query("select u.id, u.nickname, u.pointTot from User u where u.isConsultant = ?1")
    List<User> findAllByIsConsultant(boolean flag, Pageable pageable);
    @Query("select u.id, u.nickname, u.pointTot, c from User u join ConsultantProfile c on c.user.id = u.id where c.topicCategory.id = ?1")
    List<ConsultantListRes> findByTopicCategory(String topicCategoryId);



}