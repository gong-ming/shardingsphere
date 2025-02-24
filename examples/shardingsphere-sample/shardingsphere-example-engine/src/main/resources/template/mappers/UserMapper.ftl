<?xml version="1.0" encoding="UTF-8" ?>
<!--
  ~ Licensed to the Apache Software Foundation (ASF) under one or more
  ~ contributor license agreements.  See the NOTICE file distributed with
  ~ this work for additional information regarding copyright ownership.
  ~ The ASF licenses this file to You under the Apache License, Version 2.0
  ~ (the "License"); you may not use this file except in compliance with
  ~ the License.  You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  -->

<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="org.apache.shardingsphere.example.${feature?replace('-', '.')}.${framework?replace('-', '.')}.repository.UserRepository">
    <resultMap id="baseResultMap" type="org.apache.shardingsphere.example.${feature?replace('-', '.')}.${framework?replace('-', '.')}.entity.User">
        <result column="user_id" property="userId" jdbcType="INTEGER"/>
        <result column="user_name" property="userName" jdbcType="VARCHAR"/>
        <result column="pwd" property="pwd" jdbcType="VARCHAR"/>
        <result column="assisted_query_pwd" property="assistedQueryPwd" jdbcType="VARCHAR"/>
    </resultMap>

    <update id="createTableIfNotExists">
        CREATE TABLE IF NOT EXISTS t_user (user_id INT NOT NULL AUTO_INCREMENT, user_name VARCHAR(200), pwd VARCHAR(200), PRIMARY KEY (user_id));
    </update>

    <update id="truncateTable">
        TRUNCATE TABLE t_user;
    </update>

    <update id="dropTable">
        DROP TABLE IF EXISTS t_user;
    </update>

    <insert id="insert">
        INSERT INTO t_user (user_id, user_name, pwd) VALUES (${r"#{userId,jdbcType=INTEGER}, #{userName,jdbcType=VARCHAR}, #{pwd,jdbcType=VARCHAR}"})
    </insert>

    <delete id="delete">
        DELETE FROM t_user WHERE user_id = ${r"#{userId,jdbcType=INTEGER}"};
    </delete>

    <select id="selectAll" resultMap="baseResultMap">
        SELECT * FROM t_user;
    </select>
</mapper>
