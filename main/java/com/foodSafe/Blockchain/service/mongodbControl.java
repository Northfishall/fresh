package com.foodSafe.Blockchain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import net.sf.json-lib.JSONArray;
//import net.sf.json-lib.JSONObject;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.MongoCursor;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.Doc;

import static com.mongodb.client.model.Filters.exists;

public class mongodbControl {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    public mongodbControl(String dbname, String username ,String password)
    {
        try {
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
            //ServerAddress()两个参数分别为 服务器地址 和 端口
            ServerAddress serverAddress = new ServerAddress("localhost",27017);
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
            addrs.add(serverAddress);

            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
            MongoCredential credential = MongoCredential.createScramSha1Credential(username, dbname, password.toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential);

            //通过连接认证获取MongoDB连接
            mongoClient = new MongoClient(addrs,credentials);

            //连接到数据库
            mongoDatabase = mongoClient.getDatabase(dbname);
            System.out.println("Connect to database successfully");
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public mongodbControl(String dbname)
    {
        try
        {
            mongoClient = new MongoClient ("localhost",27017);
            mongoDatabase = mongoClient.getDatabase(dbname);
        }catch (Exception e)
        {
            System.out.println("error");
            System.out.println(e);
        }
    }



    public void createCollection(String collectinoName)
    {
        try{
            // 连接到 mongodb 服务
            //MongoClient mongoClient = new MongoClient( "localhost" , 27017 );


            // 连接到数据库
            //MongoDatabase mongoDatabase = mongoClient.getDatabase(dbName);
            System.out.println("Connect to database successfully");
            mongoDatabase.createCollection(collectinoName);
            System.out.println("集合创建成功");

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void insertDocument (String CollectionName , String Key1 ,String Value1)
    {
        try{
            MongoCollection<Document> collection = mongoDatabase.getCollection(CollectionName);
            System.out.println("集合"+ CollectionName + "选择成功");

            Document document = new Document(Key1,Value1);
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
            System.out.println("文档插入成功");
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void insertDocument (String CollectionName , String Key1 ,String Value1,String Key2,String Value2)
    {
        try{
            MongoCollection<Document> collection = mongoDatabase.getCollection(CollectionName);
            System.out.println("集合"+ CollectionName + "选择成功");

            Document document = new Document(Key1,Value1).append(Key2,Value2);
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
            System.out.println("文档插入成功");
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }


    //向指定collection插入信息
    public void insertDocument (String CollectionName , String Key1 ,String Value1,String Key2,String Value2,String Key3,String Value3)
    {
        try{
            MongoCollection<Document> collection = mongoDatabase.getCollection(CollectionName);
            System.out.println("集合"+ CollectionName + "选择成功");

            Document document = new Document(Key1,Value1).append(Key2,Value2).append(Key3,Value3);
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
            System.out.println("文档插入成功");
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void insertDocument (String CollectionName ,String Key,String Value,String Name,List<String> companyData)
    {
        try{
            // 连接到 mongodb 服务
            //MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // 连接到数据库
            //MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");

            MongoCollection<Document> collection = mongoDatabase.getCollection(CollectionName);
            System.out.println("集合"+ CollectionName + "选择成功");
            //插入文档
            /**
             * 1. 创建文档 org.bson.Document 参数为key-value的格式
             * 2. 创建文档集合List<Document>
             * 3. 将文档集合插入数据库集合中 mongoCollection.insertMany(List<Document>) 插入单个文档可以用 mongoCollection.insertOne(Document)
             * */
            Document document = new Document(Name,companyData).append(Key,Value);
            List<Document> documents = new ArrayList<Document>();
            documents.add(document);
            collection.insertMany(documents);
            System.out.println("文档插入成功");
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void RenewDocument(String CollectionName , String OldKey,String OldValue,String Key , ArrayList<String> Value)
    {
        try{
            // 连接到 mongodb 服务
            //MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // 连接到数据库
            //MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
            //System.out.println("Connect to database successfully");
            //List<String> Old = (List<String>) OldValue;
            List<String > New = (List<String>) Value;
            MongoCollection<Document> collection = mongoDatabase.getCollection(CollectionName);
            System.out.println("集合" + CollectionName + "选择成功");

            //Update
            collection.updateMany(Filters.eq(OldKey,OldValue), new Document("$set",new Document(Key,New)));
            //collection.updateMany({OldKey:OldValue},{$set:{Key:Value}});

            //检索查看结果
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while(mongoCursor.hasNext()){
                System.out.println(mongoCursor.next());
            }

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void DelectDocument(String CollectionName,String Key ,ArrayList<String> Value )
    {
        try{
            // 连接到 mongodb 服务
            //MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // 连接到数据库
            //MongoDatabase mongoDatabase = mongoClient.getDatabase("mycol");
            //System.out.println("Connect to database successfully");
            //int size = Value.size();
            //String[] Temp = (String[]) Value.toArray(new String[size]);
            List<String> Temp = (List<String>) Value;

            MongoCollection<Document> collection = mongoDatabase.getCollection(CollectionName);
            System.out.println("集合"+ CollectionName + "选择成功");

            //删除符合条件的第一个文档
            //collection.deleteOne(Filters.eq(Key, Value));
            //删除所有符合条件的文档
            collection.deleteMany (Filters.eq(Key, Temp));

            //collection.deleteMany(exists(Key));
            //检索查看结果
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while(mongoCursor.hasNext()){
                System.out.println(mongoCursor.next());
            }

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    public void DelectDocument(String CollectionName,String Key ,String Value )
    {
        try{
            MongoCollection<Document> collection = mongoDatabase.getCollection(CollectionName);
            System.out.println("集合"+ CollectionName + "选择成功");

            //删除符合条件的第一个文档
            //collection.deleteOne(Filters.eq(Key, Value));
            //删除所有符合条件的文档
            collection.deleteMany (Filters.eq(Key, Value));

            //collection.deleteMany(exists(Key));
            //检索查看结果
            FindIterable<Document> findIterable = collection.find();
            MongoCursor<Document> mongoCursor = findIterable.iterator();
            while(mongoCursor.hasNext()){
                System.out.println(mongoCursor.next());
            }

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
    //only one
    public String FindDocument (String CollectionName,String Key ,String Value)
    {
        MongoCollection<Document> collection = mongoDatabase.getCollection(CollectionName);
        BasicDBObject queryObj = new BasicDBObject(Key, Value);

        DB db = mongoClient.getDB( "Network" );
        DBCollection coll = db.getCollection(CollectionName);
        DBCursor cursor = coll.find(queryObj);
        System.out.println(Key+":"+Value);
        try {
            while(cursor.hasNext()) {
                BasicDBObject dbObj = (BasicDBObject) cursor.next();
                //Document item = cursor.next();
                System.out.println(dbObj);
                String address = dbObj.getString(dbObj.getString(Key));
                return address;
            }
        } finally {
            cursor.close();
        }
        return null;
    }

    public ArrayList<String> FindDocumentInf (String CollectionName,String Key ,String Value)
    {
        String Flag;
        ArrayList<String> Result = new ArrayList<String>();
        BasicDBObject queryObj = new BasicDBObject(Key, Value);
        DB db = mongoClient.getDB( "Network" );
        DBCollection coll = db.getCollection(CollectionName);
        DBCursor cursor = coll.find(queryObj);
        try {
            while(cursor.hasNext()) {
                BasicDBObject dbObj = (BasicDBObject) cursor.next();
                //Document item = cursor.next();
                //System.out.println(dbObj);
                String tag = dbObj.getString("tag");
                String Name = dbObj.getString("中文名");
                String National = dbObj.getString("国    籍");
                String MainWork = dbObj.getString("主要成就");
                //String type = dbObj.getString("中文名");
                Result.add(tag);//index 1 -> address
                Result.add(Name);//index 1 -> address
                Result.add(National);
                Result.add(MainWork);
                //Result.add(type);//index 2 -> type
            }
        } finally {
            cursor.close();
        }
        return Result;
    }

    public ArrayList<String> FindDocumentAT(String CollectionName,String Key ,String Value)
    {
        String Flag;
        ArrayList<String> Result = new ArrayList<String>();
        BasicDBObject queryObj = new BasicDBObject(Key, Value);
        DB db = mongoClient.getDB( "Network" );
        DBCollection coll = db.getCollection(CollectionName);
        DBCursor cursor = coll.find(queryObj);
        try {
            while(cursor.hasNext()) {
                BasicDBObject dbObj = (BasicDBObject) cursor.next();
                //Document item = cursor.next();
                System.out.println(dbObj);
                String address = dbObj.getString("relation");
                //String type = dbObj.getString("中文名");
                Result.add(address);//index 1 -> address
                //Result.add(type);//index 2 -> type
            }
        } finally {
            cursor.close();
        }
        return Result;
    }

    public ArrayList<String> FindDocumentAT (String CollectionName,String Key ,String Value,String Key2,String Value2)
    {
        String Flag;
        ArrayList<String> Result = new ArrayList<String>();
        BasicDBObject queryObj = new BasicDBObject(Key, Value);
        queryObj.append(Key2,Value2);
        System.out.println(queryObj);
        DB db = mongoClient.getDB( "UserDB" );
        DBCollection coll = db.getCollection(CollectionName);
        DBCursor cursor = coll.find(queryObj);
        System.out.println(cursor);
        try {
            while(cursor.hasNext()) {
                BasicDBObject dbObj = (BasicDBObject) cursor.next();
                //Document item = cursor.next();
                System.out.println(dbObj);
                String address = dbObj.getString("Address");
                String type = dbObj.getString("Type");
                //默认一个公司一个地 分公司等直接另外申请地址防止混淆 所以这里不做区分处理
                Result.add("T");//index 0 ->是否存在
                Result.add(address);//index 1 -> address
                Result.add(type);//index 2 -> type
            }
            Result.add("F");//如果存在则会放到index3 不会被读取 不存在则放在0位
        } finally {
            cursor.close();
        }
        return Result;
    }

    public ArrayList<String> FindDocumentTx (String CollectionName,String Key ,String Value)
    {
        ArrayList<String> Result = new ArrayList<String>();
        MongoCollection<Document> collection = mongoDatabase.getCollection(CollectionName);
        BasicDBObject queryObj = new BasicDBObject(Key, Value);

        DB db = mongoClient.getDB( "UserDB" );
        DBCollection coll = db.getCollection(CollectionName);
        DBCursor cursor = coll.find(queryObj);
        System.out.println(Key+":"+Value);
        try {
            while(cursor.hasNext()) {
                BasicDBObject dbObj = (BasicDBObject) cursor.next();
                //Document item = cursor.next();
                System.out.println(dbObj);
                String address = dbObj.getString("Tx");
                Result.add(address);
            }
        } finally {
            cursor.close();
        }
        return Result;
    }

    public ArrayList<String> FindDocumentTx (String CollectionName,String Key ,String Value,String Key2,String Value2)
    {
        ArrayList<String> Result = new ArrayList<String>();
        MongoCollection<Document> collection = mongoDatabase.getCollection(CollectionName);
        BasicDBObject queryObj = new BasicDBObject(Key, Value);
        queryObj.append(Key2,Value2);
        DB db = mongoClient.getDB( "UserDB" );
        DBCollection coll = db.getCollection(CollectionName);
        DBCursor cursor = coll.find(queryObj);
        System.out.println(Key+":"+Value);
        try {
            while(cursor.hasNext()) {
                BasicDBObject dbObj = (BasicDBObject) cursor.next();
                //Document item = cursor.next();
                System.out.println(dbObj);
                String address = dbObj.getString("Tx");
                Result.add(address);
            }
        } finally {
            cursor.close();
        }
        return Result;
    }

    //查询是否存在
    public String FindDocumentIC (String CollectionName,String Key ,String Value)
    {
        int flag = 0;
        BasicDBObject queryObj = new BasicDBObject(Key, Value);
        DB db = mongoClient.getDB( "UserDB" );
        DBCollection coll = db.getCollection(CollectionName);
        DBCursor cursor = coll.find(queryObj);
        while(cursor.hasNext())
        {
            flag = 1;
            BasicDBObject dbObj = (BasicDBObject) cursor.next();
            //Document item = cursor.next();
            System.out.println(dbObj);
        }
        if(flag == 1)
        {
            return "success";
        }
        else
        {
            return "fail";
        }
    }
}
