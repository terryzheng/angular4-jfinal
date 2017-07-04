#sql("findLowestScore")
    select suject,min(score) from score where score < #para(0) group by suject limit #para(1) offset #para(2);
#end