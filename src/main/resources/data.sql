-- data.sql

    INSERT INTO m_handler
    (id, "handler", keyword, response_format)
    VALUES(55, 'com.lylastudio.catetduit.handler.impl.DefaultHandler', 'keyword-not-define', 'Duh Pak ${fist_name} ${admin_name}, ga ngerti  :(');

    INSERT INTO m_handler
    (id, "handler", keyword, response_format)
    VALUES(53, 'com.lylastudio.catetduit.handler.impl.DefaultHandler', 'agus;gus;agus?;gus?', 'Iya pak ${fist_name} ada yg bisa ${admin_name} bantu ? :)');

    INSERT INTO m_handler
    (id, "handler", keyword, response_format)
    VALUES(54, 'com.lylastudio.catetduit.handler.impl.TransactionHandler', 'insert-trx', 'Ok Pak ${fist_name} sudah ${admin_name} catet ya hehe.');

