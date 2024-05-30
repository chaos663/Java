package com.honey.spring.repository;

import com.honey.spring.domain.Member;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// JdbcTemplate을 사용하여 MemberRepository를 구현하는 클래스
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    // 생성자에서 DataSource를 받아와 JdbcTemplate을 초기화
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Member를 데이터베이스에 저장하는 메서드
    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id"); // 테이블명과 자동 생성되는 키 컬럼 설정

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName()); // 입력할 파라미터 설정

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)); // 키 값을 반환받음
        member.setId(key.longValue()); // 반환받은 키 값을 member 객체에 설정
        return member; // 저장된 member 객체 반환
    }

    // ID를 통해 Member를 찾는 메서드
    @Override
    public Optional<Member> findById(Long id) {
        List<Member> result = jdbcTemplate.query("select * from member where id= ?", memberRowMapper(), id); // ID를 조건으로 쿼리 실행
        return result.stream().findAny(); // 결과를 Optional로 반환
    }

    // 이름을 통해 Member를 찾는 메서드
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name); // 이름을 조건으로 쿼리 실행
        return result.stream().findAny(); // 결과를 Optional로 반환
    }

    // 모든 Member를 찾는 메서드
    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper()); // 모든 멤버를 찾는 쿼리 실행
    }

    // ResultSet을 Member 객체로 매핑하는 RowMapper
    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id")); // ResultSet에서 id를 가져와 설정
            member.setName(rs.getString("name")); // ResultSet에서 name을 가져와 설정
            return member; // 매핑된 Member 객체 반환
        };
    }
}
