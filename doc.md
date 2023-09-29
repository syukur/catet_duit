
sample request from telegram to our webhook :

{
    "message" : {
        "date" : 1695863442,
        "chat" : {
            "id" : 70855858,
            "last_name" : null,
            "first_name" : "Syukur Kurnia",
            "username" : "syukur_kurnia"
        },
        "from" : {
            "id" : 70855858,
            "first_name" : "Syukur Kurnia",
            "last_name" : null,
            "username" : "syukur_kurnia"
        },
        "text" : "ok",
        "message_id" : 217
    },
    "update_id" : 66919793
}

1. input expense
---------------------------------------------------------
expense 10000 personal "by book"
expense [amount] [category] [note]
---------------------------------------------------------


2. daily report
---------------------------------------------------------
daily-report : show list expense of current date

daily-report 20230901
daily-report [date]
---------------------------------------------------------


3. add category
---------------------------------------------------------
category Personal
category "Orang Tua"
category [category name]
---------------------------------------------------------


4. show category list
---------------------------------------------------------
category.list : show all category
---------------------------------------------------------