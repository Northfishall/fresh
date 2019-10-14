<!-- 引入 echarts.js -->
<script src="./dist/echarts.js"></script>
    <script type="text/javascript" src="jquery.js"></script>
    <!-- 引入网络图 -->

    <link href="./layui/css/layui.css" rel="stylesheet" type="text/css"/>
    <script src="./layui/layui.js" type="text/javascript"></script>
    <script src="./layui/layui.js"></script>

    <script>/*Fixing iframe window.innerHeight 0 issue in Safari*/document.body.clientHeight;</script>
<script src="https://gw.alipayobjects.com/os/antv/pkg/_antv.g6-3.0.5-beta.12/build/g6.js"></script>
    <script src="https://gw.alipayobjects.com/os/antv/assets/lib/jquery-3.2.1.min.js"></script>
    <script src="https://gw.alipayobjects.com/os/antv/assets/lib/d3-4.13.0.min.js"></script>
    <style>
.g6-tooltip {
    border: 1px solid #e2e2e2;
    border-radius: 4px;
    font-size: 12px;
    color: #545454;
    background-color: rgba(255, 255, 255, 0.9);
    padding: 10px 8px;
    box-shadow: rgb(174, 174, 174) 0px 0px 10px;
}
</style>

function draw(file_data){

    function clearAllStats() {
        graph.setAutoPaint(false);
        graph.getNodes().forEach(function(node) {
            graph.clearItemStates(node);
        });
        graph.getEdges().forEach(function(edge) {
            graph.clearItemStates(edge);
        });
        graph.paint();
        graph.setAutoPaint(true);
    }
    graph.on('node:mouseenter', function(e) {
        var item = e.item;
        graph.setAutoPaint(false);
        graph.getNodes().forEach(function(node) {
            graph.clearItemStates(node);
            graph.setItemState(node, 'dark', true);
        });
        graph.setItemState(item, 'dark', false);
        graph.setItemState(item, 'highlight', true);
        graph.getEdges().forEach(function(edge) {
            if (edge.getSource() === item) {
                graph.setItemState(edge.getTarget(), 'dark', false);
                graph.setItemState(edge.getTarget(), 'highlight', true);
                graph.setItemState(edge, 'highlight', true);
                edge.toFront();
            } else if (edge.getTarget() === item) {
                graph.setItemState(edge.getSource(), 'dark', false);
                graph.setItemState(edge.getSource(), 'highlight', true);
                graph.setItemState(edge, 'highlight', true);
                edge.toFront();
            } else {
                graph.setItemState(edge, 'highlight', false);
            }
        });
        graph.paint();
        graph.setAutoPaint(true);
    });
    graph.on('node:mouseleave', clearAllStats);
    graph.on('canvas:click', clearAllStats);
    $.getJSON(file_data, function(data) {
        graph.data({
            nodes: data.nodes,
            edges: data.edges.map(function(edge, i) {
                edge.id = 'edge' + i;
                return Object.assign({}, edge);
            })
        });
        var simulation = d3.forceSimulation().force("link", d3.forceLink().id(function(d) {
            return d.id;
        }).strength(0.5)).force("charge", d3.forceManyBody()).force("center", d3.forceCenter(window.innerWidth / 2, window.innerHeight / 2));
        simulation.nodes(data.nodes).on("tick", ticked);
        simulation.force("link").links(data.edges);

        graph.render();

        function ticked() {
            graph.refreshPositions();
            graph.paint();
        }
    });
}