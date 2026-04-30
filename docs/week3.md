## 3주차 세미나(이론) 내용 정리

### JPA Auditing

BaseTimeEntity : 생성일/수정일 자동 관리

코드에서..
@Getter
@MappedSuperclass      
@EntityListeners(AuditingEntityListener.class)   : Auditing 기능을 포함
public abstract class BaseTimeEntity {

    @CreatedDate           : Entity가 생성되어 저장될 때 시간이 자동 저장됨
    private LocalDateTime createdAt;

    @LastModifiedDate       : 조회한 Entity의 값을 변경할 떄 시간이 자동 저장
    private LocalDateTime updatedAt;
}

