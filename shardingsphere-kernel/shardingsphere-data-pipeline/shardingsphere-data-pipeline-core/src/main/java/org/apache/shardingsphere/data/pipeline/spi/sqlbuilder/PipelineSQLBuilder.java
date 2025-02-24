/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.data.pipeline.spi.sqlbuilder;

import org.apache.shardingsphere.data.pipeline.api.ingest.record.Column;
import org.apache.shardingsphere.data.pipeline.api.ingest.record.DataRecord;
import org.apache.shardingsphere.data.pipeline.core.record.RecordUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Pipeline SQL builder.
 */
public interface PipelineSQLBuilder {
    
    /**
     * Build insert SQL.
     *
     * @param dataRecord data record
     * @return insert SQL
     */
    String buildInsertSQL(DataRecord dataRecord);
    
    /**
     * Build update SQL.
     *
     * @param dataRecord data record
     * @param conditionColumns condition columns
     * @return update SQL
     */
    String buildUpdateSQL(DataRecord dataRecord, Collection<Column> conditionColumns);
    
    /**
     * Extract need updated columns.
     *
     * @param columns the input columns
     * @param record the input datarecord
     * @return the filtered columns.
     */
    default List<Column> extractUpdatedColumns(Collection<Column> columns, DataRecord record) {
        return new ArrayList<>(RecordUtil.extractUpdatedColumns(record));
    }
    
    /**
     * Build delete SQL.
     *
     * @param dataRecord data record
     * @param conditionColumns condition columns
     * @return delete SQL
     */
    String buildDeleteSQL(DataRecord dataRecord, Collection<Column> conditionColumns);
    
    /**
     * Build truncate SQL.
     *
     * @param tableName table name
     * @return truncate SQL
     */
    String buildTruncateSQL(String tableName);
    
    /**
     * Build count SQL.
     *
     * @param tableName table name
     * @return count SQL
     */
    String buildCountSQL(String tableName);
    
    /**
     * Build check empty SQL.
     *
     * @param tableName table name
     * @return check SQL
     */
    String buildCheckEmptySQL(String tableName);
    
    /**
     * Build split by primary key range SQL.
     *
     * @param tableName table name
     * @param primaryKey primary key
     * @return split SQL
     */
    String buildSplitByPrimaryKeyRangeSQL(String tableName, String primaryKey);
}
