package com.bw.looters.ms.web.ctrl;

import com.bw.looters.ms.biz.facade.MsFacade;
import com.bw.looters.ms.web.vo.req.TreeNodesRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ms")
public class MsCtrl {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 登录操作
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String username, @RequestParam String password) {
        LOGGER.trace("{}-{}", username, password);
        return "main";

//        // check the submitted username and password
//        if (username.equals("root") && password.equals("root")) {
//           return "main";
//          // return "redirect:/ms/mainTreeNodes";
//        } else {
//            System.out.println(" \n\n\n Username got is " + username);
//            System.out.println(" \n\n\n Password got is " + password);
//            return "index";
//        }
    }

    /**
     * TODO - zhYou: 修改返回数据, 与使其继承自Res对象; 查看TreeLoader中的配置
     *
     * @return 树节点配置
     */
    @ResponseBody
    @RequestMapping("/mainTreeNodes")
    public Set<TreeNodesRes.TreeNode> mainTreeNodes() {
        // LOGGER.trace("{}-{}", mainTreeNodes());

        Set<TreeNodesRes.TreeNode> nodes = new TreeSet<TreeNodesRes.TreeNode>();

        // 管理平台
        TreeNodesRes.TreeNode ms = new TreeNodesRes.TreeNode(1000, "platform", false);
        ms.addChildren(new TreeNodesRes.TreeNode(1001, "user", true));
        ms.addChildren(new TreeNodesRes.TreeNode(1002, "Competence", true));
        nodes.add(ms);

        // 小小战争
        TreeNodesRes.TreeNode looters = new TreeNodesRes.TreeNode(2000, "Knights demolitions", false);
        TreeNodesRes.TreeNode confNode = new TreeNodesRes.TreeNode(2100, "Configuration", false);
        confNode.addChildren(new TreeNodesRes.TreeNode(2101, "Building Information", true));
        confNode.addChildren(new TreeNodesRes.TreeNode(2102, "Building Level", true));
        confNode.addChildren(new TreeNodesRes.TreeNode(2103, "Information role", true));
        confNode.addChildren(new TreeNodesRes.TreeNode(2104, "Character Level", true));
        confNode.addChildren(new TreeNodesRes.TreeNode(2105, "server", true));
        confNode.addChildren(new TreeNodesRes.TreeNode(2106, "Constant server", true));
        confNode.addChildren(new TreeNodesRes.TreeNode(2107, "User Initialization", true));
        confNode.addChildren(new TreeNodesRes.TreeNode(2108, "Accelerated consumption", true));
        confNode.addChildren(new TreeNodesRes.TreeNode(2109, "Shop Exchange", true));
        confNode.addChildren(new TreeNodesRes.TreeNode(2110, "Character Level", true));

        TreeNodesRes.TreeNode dataNode = new TreeNodesRes.TreeNode(2200, "data", false);
        dataNode.addChildren(new TreeNodesRes.TreeNode(2201, "Character", true));
        dataNode.addChildren(new TreeNodesRes.TreeNode(2202, "blacklist", true));
        dataNode.addChildren(new TreeNodesRes.TreeNode(2203, "gem", true));
        dataNode.addChildren(new TreeNodesRes.TreeNode(2204, "platform", true));

        looters.addChildren(confNode);
        looters.addChildren(dataNode);
        nodes.add(looters);

        System.out.println("\n\n\tRETURNING  NODES  \n\n" + nodes);
        LOGGER.trace("{}-{}", nodes);

        return nodes;
        // TreeNodesRes res = new TreeNodesRes();
        // res.setNodes(nodes);
        // return res;

    }

    @Resource
    private MsFacade msFacade;
    private static final Logger LOGGER = LoggerFactory.getLogger(MsCtrl.class);
}
