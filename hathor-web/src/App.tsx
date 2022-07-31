/*
 * @Description: 
 * @Author: ywj
 * @Date: 2022-05-16 09:37:15
 */

import React, { useRef } from "react";
import { Select, Button,Tabs } from "antd";
import AceEditor from "react-ace";

import "ace-builds/src-noconflict/mode-mysql";
import "ace-builds/src-noconflict/theme-kuroir";
import "ace-builds/src-noconflict/theme-monokai";
import "ace-builds/src-noconflict/ext-language_tools";
import TreeGraph from './components/Graph/treeGraph';
import './App.css';
const { Option } = Select;
const {TabPane } = Tabs;
function App() {
  const editorRef = useRef<any>(null);
  const onChange = (newValue: any) => {
    console.log("change", newValue);
  }
  if (editorRef.current) {
    const editor = editorRef.current.editor;
    console.log(editor, 'editor');
  }
  const handleChange = () => {

  }
  return (
    <div className="App content-box">
      <div className="left-box">
        <div className="left-top">
          <Select defaultValue="mySql" onChange={handleChange} className="select-lang">
            <Option value="jack">mySql</Option>
          </Select>
          <Button type="primary" className="run-btn">运行</Button>
        </div>
        <AceEditor
          mode="mysql"
          theme="kuroir"
          onChange={onChange}
          name="UNIQUE_ID_OF_DIV"
          fontSize={14}
          showPrintMargin={true}
          showGutter={true}
          highlightActiveLine={true}
          height="calc(100% - 90px)"
          width="100%"
          ref={editorRef}
          setOptions={{
            enableBasicAutocompletion: false,
            enableLiveAutocompletion: true,
            enableSnippets: true,
            showLineNumbers: true,
            tabSize: 2,
          }}
          value={ `CREATE VIEW vsal 
          AS 
            SELECT a.deptno                  "Department", 
                   a.num_emp / b.total_count "Employees", 
                   a.sal_sum / b.total_sal   "Salary" 
            FROM   (SELECT deptno, 
                           Count()  num_emp, 
                           SUM(sal) sal_sum 
                    FROM   scott.emp 
                    WHERE  city = 'NYC' 
                    GROUP  BY deptno) a, 
                   (SELECT Count()  total_count, 
                           SUM(sal) total_sal 
                    FROM   scott.emp 
                    WHERE  city = 'NYC') b 
          ;
          
          INSERT ALL
            WHEN ottl < 100000 THEN
              INTO small_orders
                VALUES(oid, ottl, sid, cid)
            WHEN ottl > 100000 and ottl < 200000 THEN
              INTO medium_orders
                VALUES(oid, ottl, sid, cid)
            WHEN ottl > 200000 THEN
              into large_orders
                VALUES(oid, ottl, sid, cid)
            WHEN ottl > 290000 THEN
              INTO special_orders
          SELECT o.order_id oid, o.customer_id cid, o.order_total ottl,
          o.sales_rep_id sid, c.credit_limit cl, c.cust_email cem
          FROM orders o, customers c
          WHERE o.customer_id = c.customer_id;`}
        // editorProps={{ $blockScrolling: true }}
        // style={{width:'1000px'}}
        />
      </div>
      <div className="right-box">
        <div className="card-container">
        <Tabs defaultActiveKey="1" onChange={onChange} >
          <TabPane tab="SQLFlow" key="1">
          <TreeGraph/>
          </TabPane>
          <TabPane tab="Table level lineage" key="2">
          Table level lineage
          </TabPane>
          <TabPane tab="Text output" key="3">
          Text output
          </TabPane>
        </Tabs>
        </div>
        
      </div>
    </div>
  );
}

export default App;
