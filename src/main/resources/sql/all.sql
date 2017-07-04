#define selectByIdList(table, idList)
select * from #(table) where id in (
    #for (id : idList)
        #(for.index > 0 ? "," : "") #(id)
    #end
)
#end

#include("student.sql")

#namespace("score")
    #include("score.sql")
#end