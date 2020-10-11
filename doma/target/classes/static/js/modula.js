/*
    创建组件,fn(num,name,value,child)
    num 1元素   3文本
    name  当num=1 标签名  当num=3 文本内容
    value   属性设置对象含有.name .value数组
    child   子容器
*/
function createModula(num = null, name = null, value = null, child = false) {
    //创建元素或文本
    var module;
    if (num == null || name == null) {
        return false;
    }
    if (num == 1) {
        module = document.createElement(name);
    } else if (num == 3) {
        module = document.createTextNode(name);
    }

    //判断是否有子集
    if (typeof child === typeof document && (child.nodeType == 1 || child.nodeType == 3)) {
        module.appendChild(child);
    } else if (typeof child === typeof document && child.length > 0) {
        for (var i of child) {
            module.appendChild(i);
        }
    }

    //判断是否有属性设置
    if (value != null && typeof value == typeof document) {
        var value_name = value.name;
        var value_key = value.value;
        for (var i in value_name) {
            module.setAttribute(value_name[i], value_key[i]);
        }
    }

    return module;
}