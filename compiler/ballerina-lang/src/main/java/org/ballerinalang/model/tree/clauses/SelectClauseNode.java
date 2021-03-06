/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ballerinalang.model.tree.clauses;

import org.ballerinalang.model.tree.Node;

import java.util.List;

/**
 * @since 0.965.0
 *
 * This interface represents the SELECT clause in Streams/Tables grammar in Ballerina.
 * <pre>Grammar:
 *      SELECT (MUL| ( selectExpression (COMMA selectExpression)* ) ) groupByClause? havingClause?
 *
 * E.g.
 *      select * having age > 20 AND score > 10;
 *      select name, school group by name, school having age > 20 AND score > 10;
 * </pre>
 *
 */
public interface SelectClauseNode extends Node {

    void setSelectExpressions(List<? extends SelectExpressionNode> selectExpressions);

    List<? extends SelectExpressionNode> getSelectExpressions();

    void setSelectAll(boolean isSelectAll);

    boolean isSelectAll();

    void setGroupBy(GroupByNode groupByNode);

    GroupByNode getGroupBy();

    void setHaving(HavingNode havingNode);

    HavingNode getHaving();
}
