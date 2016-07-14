aspect test {
    pointcut newRoute():
         call(spark.Route spark.Route+.new()) && args();

    after(): newRoute(){
        System.out.println("Ok");
    }
}