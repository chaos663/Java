package com.honey.spring.repository;

import com.honey.spring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

public class JdbcMemberRepository implements MemberRepository {

    private final DataSource dataSource;

    // DataSource를 주입받아 초기화하는 생성자
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)"; // 회원 정보를 저장하는 SQL 쿼리

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection(); // 데이터베이스 연결 획득
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS); // 자동 생성된 키 반환 설정

            pstmt.setString(1, member.getName()); // SQL 쿼리에 회원 이름 설정

            pstmt.executeUpdate(); // 쿼리 실행
            rs = pstmt.getGeneratedKeys(); // 생성된 키 반환

            if (rs.next()) {
                member.setId(rs.getLong(1)); // 생성된 ID를 멤버 객체에 설정
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs); // 자원 해제
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource); // DataSourceUtils를 사용해 연결 획득
    }

//    public static Connection getConnection() {
//        try {
//            return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
//        } catch (SQLException e) {
//            throw new IllegalStateException("Database connection failed", e);
//        }
//    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close(); // ResultSet 해제
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close(); // PreparedStatement 해제
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                conn.close(); // Connection 해제
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?"; // ID로 회원을 찾는 SQL 쿼리

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection(); // 데이터베이스 연결 획득
            pstmt = conn.prepareStatement(sql); // SQL 쿼리 준비
            pstmt.setLong(1, id); // 쿼리에 ID 설정

            rs = pstmt.executeQuery(); // 쿼리 실행

            if (rs.next()) {
                Member member = new Member(); // 회원 객체 생성
                member.setId(rs.getLong("id")); // ID 설정
                member.setName(rs.getString("name")); // 이름 설정
                return Optional.of(member); // Optional로 반환
            } else {
                return Optional.empty(); // 결과가 없으면 빈 Optional 반환
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs); // 자원 해제
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?"; // 이름으로 회원을 찾는 SQL 쿼리

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection(); // 데이터베이스 연결 획득
            pstmt = conn.prepareStatement(sql); // SQL 쿼리 준비
            pstmt.setString(1, name); // 쿼리에 이름 설정

            rs = pstmt.executeQuery(); // 쿼리 실행

            if (rs.next()) {
                Member member = new Member(); // 회원 객체 생성
                member.setId(rs.getLong("id")); // ID 설정
                member.setName(rs.getString("name")); // 이름 설정
                return Optional.of(member); // Optional로 반환
            }
            return Optional.empty(); // 결과가 없으면 빈 Optional 반환
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs); // 자원 해제
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "SELECT * FROM MEMBER"; // 모든 회원을 찾는 SQL 쿼리

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection(); // 데이터베이스 연결 획득
            pstmt = conn.prepareStatement(sql); // SQL 쿼리 준비

            rs = pstmt.executeQuery(); // 쿼리 실행

            List<Member> members = new ArrayList<>(); // 결과를 담을 리스트
            while (rs.next()) {
                Member member = new Member(); // 회원 객체 생성
                member.setId(rs.getLong("id")); // ID 설정
                member.setName(rs.getString("name")); // 이름 설정
                members.add(member); // 리스트에 추가
            }
            return members; // 리스트 반환
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs); // 자원 해제
        }
    }
}
