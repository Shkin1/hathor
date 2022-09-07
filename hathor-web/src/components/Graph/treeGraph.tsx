/*
 * @Description: 
 * @Author: ywj
 * @Date: 2022-07-31 17:56:12
 */
import React, { useRef,useEffect} from 'react';
import G6 from '@antv/g6';


const TreeGraph: React.FC = () => { 
  const graphRef = useRef<any>(null);
  useEffect(() => { 
    treeData(); 
  },[])
  const treeData = () => { 
    fetch('https://gw.alipayobjects.com/os/antvdemo/assets/data/algorithm-category.json')
  .then((res) => res.json())
      .then((data) => {
        if (graphRef.current) { 
          // const container = document.getElementById('container');
    const width = graphRef.current.scrollWidth;
    const height = graphRef.current.scrollHeight || 840;
    const graph = new G6.TreeGraph({
      container:graphRef.current,
      width,
      height,
      modes: {
        default: [
          {
            type: 'collapse-expand',
            onChange: function onChange(item, collapsed) {
              const data = item?.get('model');
              data.collapsed = collapsed;
              return true;
            },
          },
          'drag-canvas',
          'zoom-canvas',
        ],
      },
      defaultNode: {
        size: 26,
        anchorPoints: [
          [0, 0.5],
          [1, 0.5],
        ],
      },
      defaultEdge: {
        type: 'cubic-horizontal',
      },
      layout: {
        type: 'dendrogram',
        direction: 'LR', // H / V / LR / RL / TB / BT
        nodeSep: 30,
        rankSep: 100,
      },
    });

    graph.node(function (node) {
      return {
        label: node.id,
        labelCfg: {
          position: node.children && node.children.length > 0 ? 'left' : 'right',
          offset: 5,
        },
      };
    });

    graph.data(data);
    graph.render();
    graph.fitView();

    // if (typeof window !== 'undefined')
    //   window.onresize = () => {
    //     if (!graph || graph.get('destroyed')) return;
    //     if (!container || !container.scrollWidth || !container.scrollHeight) return;
    //     graph.changeSize(container.scrollWidth, container.scrollHeight);
    //   };
        }
    
  });
  }
  return (
    <div>
      <div ref={ graphRef}></div>
   </div>
 ) 
} 
export default TreeGraph;