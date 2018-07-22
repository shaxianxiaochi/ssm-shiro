//JavaScript代码区域
layui.use(['element','jquery','layedit', 'laydate'], function(){
    var element = layui.element;
    var $ = layui.jquery;

    var active = {
        getAllUser:function () {
            var url = "../DoUserServlet";
            var params = {
              "opr":"getAll"
            };
            $.post(url,params,function (data) {
                console.log(data);
                var content = "";
                if(data.userList == null){
                    content = '<blockquote class="layui-elem-quote">没有数据！</blockquote>';
                } else {
                    content += '<table class="layui-table">';
                    $(data.userList).each(function () {
                       content += '<tr>';
                       content += '<td>'+this.userCode+'</td>';
                       content += '<td>'+this.userName+'</td>';
                       content += '<td>'+this.userPassword+'</td>';
                       content += '<td>'+this.gender+'</td>';

                       content += '</tr>';
                    });
                    content += '</table>';
                    element.tabAdd('demo', {
                        title: '所有用户' //用于演示
                        ,content: content
                        ,id: 12
                    })
                }
            },"JSON");

        },
        addUser:function () {
            var html = '<form class="layui-form layui-form-pane" action="">';
            html += '<div class="layui-form-item">';
            html += '   <label class="layui-form-label">用户编码</label>';
            html += '   <div class="layui-input-block">';
            html += '       <input type="text" name="title" autocomplete="off" placeholder="请输入用户编码" class="layui-input">';
            html += '   </div>';
            html += '</div>';
            html += '<div class="layui-form-item">';
            html += '   <label class="layui-form-label">用户姓名</label>';
            html += '   <div class="layui-input-block">';
            html += '       <input type="text" name="title" autocomplete="off" placeholder="请输入用户姓名" class="layui-input">';
            html += '   </div>';
            html += '</div>';
            html += '<div class="layui-form-item">';
            html += '   <label class="layui-form-label">用户密码 </label>';
            html += '   <div class="layui-input-block">';
            html += '       <input type="text" name="title" autocomplete="off" placeholder="请输入用户姓名" class="layui-input">';
            html += '   </div>';
            html += '</div>';
            html += '';
            html += '';
            html += '';
            html += '</form>';
            element.tabAdd('demo', {
                title: '添加用户'
                ,content: html
                ,id: 13
            })
        }
    };

    $('.site-demo-active').on('click', function(){
        var othis = $(this), type = othis.data('type');
        active[type] ? active[type].call(this, othis) : '';
    });
});