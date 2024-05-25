CREATE SEQUENCE seq_mvc_board6;

CREATE TABLE mvc_board6(
    bId NUMBER(4) PRIMARY KEY,
    bName VARCHAR2(20),
    bTitle VARCHAR2(100),
    bContent VARCHAR2(300),
    bDate DATE DEFAULT SYSDATE,
    bHit NUMBER(4) DEFAULT 0,
    bGroup NUMBER(4),
    bStep NUMBER(4) DEFAULT 0,
    bIndent NUMBER(4) DEFAULT 0,
    bLike VARCHAR2(5) DEFAULT 'N'
);

-- DUMMY DATA
INSERT INTO mvc_board6
(bId, bName, bTitle, bContent, bGroup)
VALUES
(seq_mvc_board6.nextval, '??øª', '????1', '????1', seq_mvc_board6.CURRVAL);

COMMIT;