package org.seminify.app.repository;

import java.util.List;

import org.seminify.app.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);

    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

    void deleteByMnoLessThan(Long mno);

    @Query("SELECT M FROM Memo M ORDER BY M.mno DESC")
    List<Memo> getListDesc();

    // @Modifying
    // @Query("UPDATE Memo M SET M.memoText = :memoText WHERE M.mno = :mno")
    // @Transactional
    // int updateMemoText(@Param("mno") Long mno, @Param("memoText") String
    // memoText);
    @Modifying
    @Query("UPDATE Memo M SET M.memoText = :#{#param.memoText} WHERE M.mno = :#{#param.mno}")
    @Transactional
    int updateMemoText(@Param("param") Memo memo);

    @Query(countQuery = "SELECT COUNT(M) FROM Memo M WHERE M.mno > :mno", value = "SELECT M FROM Memo M WHERE M.mno > :mno")
    Page<Memo> getListWithQuery(Long mno, Pageable pageable);

    @Query(countQuery = "SELECT COUNT(M) FROM Memo M WHERE M.mno > :mno", value = "SELECT M.mno, M.memoText, CURRENT_DATE FROM Memo M WHERE M.mno > :mno")
    Page<Object[]> getListWithQueryObject(Long mno, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM TBL_MEMO WHERE MNO > 0")
    List<Object[]> getNativeResult();
}
