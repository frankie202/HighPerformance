package com.frankie.datastructure.tree.redblackTree;

public class RedBlackTree {
    public static final int RED = 1;
    public static final int BLACK = 0;
    private Node root;


    public RedBlackTree(int value) {
        this.root = new Node(value,BLACK);
    }

    /*插入*/
    public void insert(int value) {
        Node tmp = this.root;
        Node newNode = new Node(value,RED);
        /*找到该节点所处的位置*/
        for (;;) {
            if (tmp == null) {
                tmp = newNode;
                return;
            } else {
                if (tmp.value > value) {
                    if (tmp.left != null) {
                        tmp = tmp.left;
                    } else {
                        tmp.left = newNode;
                        newNode.parent = tmp;
                        break;
                    }
                } else {
                    if (tmp.right != null) {
                        tmp = tmp.right;
                    } else {
                        tmp.right = newNode;
                        newNode.parent = tmp;
                        break;
                    }
                }
            }
        }
        //插入节点后，进行平衡
        this.balanceAfterInsert(newNode);
    }

    /**
     * 插入后的自平衡
     * @param node
     * 理解？？？？
     */
    private void balanceAfterInsert(Node node) {
        node.color = RED;
        Node parent;
        Node pParent;
        Node pParentLeft,pParentRight;

        for (;;) {
            /*没有父节点*/
            if ((parent = node.parent) == null) {
                node.color = BLACK;
                return;
            } else  if ((parent.color == BLACK) || (pParent = parent.parent) ==null) {
                /*父节点为黑色或者没有祖父节点*/
                return;
            }

            if (parent == (pParentLeft = pParent.left)) {
                if ((pParentRight = pParent.right) != null && pParentRight.color == RED) {
                    pParentRight.color = BLACK;
                    parent.color = BLACK;
                    pParent.color = RED;
                    node = pParent;
                } else {
                    if (node == parent.right) {
                        leftRotate(parent);
                        Node temp = node;
                        node = parent;
                        parent = temp;
                    }
                    parent.color = BLACK;
                    pParent.color = RED;
                    rightRotate(pParent);
                }
            } else {
                if ((pParentLeft = pParent.left) != null && pParentLeft.color == RED) {
                    pParentLeft.color = BLACK;
                    parent.color = BLACK;
                    pParent.color = RED;
                    node = pParent;
                } else {
                    if (node == parent.left) {
                        rightRotate(parent);
                        Node temp = node;
                        node = parent;
                        parent = temp;
                    }
                    parent.color = BLACK;
                    pParent.color = RED;
                    leftRotate(pParent);
                }
            }
        }

    }

    public Node query(int value) {
        Node tmp =this.root;
        while (tmp != null) {
            if (value > tmp.value) {
                tmp = tmp.right;
            } else if (value == tmp.value) {
                return tmp;
            } else {
                tmp = tmp.left;
            }
        }
        return null;
    }

    /*删除*/
    public void delete(int value) {
        delete(query(value));
    }

    private void delete(Node node) {
        if (node == null) {
            return;
        }

        Node child,parent,replace;
        int color = 0;
        /*总体上删除分为两种情况，待删除节点有双子节点，或者待删除节点只有左子节点或右子节点*/

        /*待删除节点有双子节点*/
        if (node.left != null && node.right != null) {
            /*找到替换节点，要替换删除节点的节点*/
            replace = successor(node);/*查找右子节点的最小值*/
            /*替换节点的父节点*/
            parent = replace.parent;
            /*因为替换节点是右子树的最小值，索引替换节点不可能存在左子节点，只可能存在右子节点*/
            child = replace.right;

            /*
            *为什么要获取替换节点的颜色？
            * 因为替换节点的指针最终会丢失，替换节点即将代替带删除节点，替换节点就不能再保留，
            * 说明替换节点处会缺失节点，因此需要判断节点的颜色
            * 若缺少的是黑色节点，会破坏红黑树的平衡性
            */
            color = replace.color;

            if (node == replace.parent)  {
                /*若替换节点的父节点即为待删除节点，直接将待删除节点的指针赋值给parent*/
                parent = replace;
            } else {
                /*若替换节点的父节点不是待删除节点*/
                if (child != null) {
                    /*
                    * 替换节点是要删除的，因为他的值实惠别放置到待删除节点中，把替换节点删除就相当于完成删除节点
                    * 因此要替换节点的子节点必须找到父节点
                    */
                    this.setParent(child,replace.parent);
                }

                /*替换节点的右孩子设置为替换节点父节点的左孩子*/
                replace.parent.left = child;//将代替节点的子节点赋给替代节点父节点的左子节点
                replace.right = node.right;/*将替换节点移动到删除节点的位置*/
                setParent(node.right,replace);
            }
            setParent(replace,node.parent);
            replace.left = node.left;
            setParent(node.left,replace);

            setColor(replace,node.color);

            if (node.parent != null) {
                //待删除节点的父节点不为空，调整待删除父节点的左右孩子指针
                if (node.parent.left == node) {
                    node.parent.left = replace;
                } else  {
                    node.parent.right = replace;
                }
            } else {
                this.root = replace;
            }

            if (color == BLACK) {
                balanceDeletion(child,parent);
            }
        } else {
            /*待删除节点只有单子节点*/
            if (node.left != null) {
                replace = node.left;
            } else  {
                replace = node.right;
            }

            parent = node.parent;
            if (parent != null) {
                //判断待删除节点是左子节点还是右子节点
                if (parent.left == node) {
                    parent.left = replace;
                } else {
                    parent.right = replace;
                }
            } else  {
                //若待删除节点的父节点为null，说明删除的是根节点
                this.root = replace;
            }
            /*替换节点的父节点设为删除节点的父节点*/
            setParent(replace,parent);

            color = node.color;
            child = replace;
            //若删除的是黑色节点需要重新调整红黑树的平衡
            if (color == BLACK) {
                balanceDeletion(child,parent);
            }
        }

    }

    /**
     * 红黑树删除后的平衡：
     *
     * @param node
     * @param parent
     */
    private void balanceDeletion(Node node, Node parent) {
        Node other;

        while ((node.color == BLACK) && (node != this.root)) {
            /*
            * 假设替换节点的颜色也为黑色，进入该方法的前提是删除节点是黑色
            * 替换节点也是黑色，需要调整，
            */

            if (parent.left == node) {
                //替换节点是新父节点的左子节点，
                other = parent.right;/*other是新父节点的右子节点*/
                if (RED == other.color) {/*右子节点是红色*/
                    /*假设兄弟是红颜色，父节点一定是黑色
                    * 将父子颜色互换，并进行左旋
                    * */
                    parent.color = RED;
                    other.color = BLACK;
                    leftRotate(parent);
                } else  {
                    if (isBlack(other.left) && isBlack(other.right)) {
                        /*
                         * 若兄弟节点没有任何子节点，会进入该分支
                         * 那么other是替换节点的兄弟节点
                         */
                        /*other染红，父指针向上回溯*/
                        other.color = RED;
                        node = parent;
                        parent = node.parent;
                    } else if (isRed(other.left) && isBlack(other.right)) {
                        other.color = RED;
                        other.left.color = BLACK;
                        rightRotate(other);
                    } else if (isRed(other.right)) {
                        setColor(other,parent.color);
                        parent.color = BLACK;
                        other.right.color = BLACK;
                        leftRotate(parent);
                        break;
                    }
                }
            } else {
                other = parent.left;
                if (other.color == RED) {
                    other.color = BLACK;
                    parent.color = RED;
                    rightRotate(parent);
                } else  {
                    if (isBlack(other.left) && isBlack(other.right)) {
                        other.color = RED;
                        node = parent;
                        parent = node.parent;
                    } else if (isRed(other.right) && isBlack(other.left)) {
                        parent.color = RED;
                        other.color = BLACK;
                        leftRotate(other);
                    } else if (isRed(other.left)) {
                        setColor(other,parent.color);
                        parent.color = BLACK;
                        other.left.color = BLACK;
                        rightRotate(parent);
                        break;
                    }

                }
            }
        }

        node.color = BLACK;
    }

    public boolean isRed(Node node) {
        return (node != null && node.color == RED) ? true : false;

    }

    public boolean isBlack(Node node) {
        return !isRed(node);
    }

    public void setParent(Node node, Node parent) {
        if (node != null) {
            node.parent = parent;
        }
    }

    public void setColor(Node node, int color) {
        if (node != null) {
            node.color = color;
        }
    }

    /*寻找后继结点，即大于该节点的最小节点*/
    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    /**
     * 寻找待删除节点的后继结点
     * （因为该节点要被删除，所以需要站到该节点的后继节点补充到该位置）
     * 规则：
     *  要么是是左子树的最大值，要么是右子树的最小值
     * @param node
     * @return
     */
    private Node successor(Node node) {
        if (node.right != null) {
            return min(node.right);
        }

        /*下面是不会进入的， 因为只有node的两个子节点部位null时才会进入该方法
        --》在调用该方法的条件中，前提是左右子节点同时不为空*/
        Node tmp = node.parent;
        while ((tmp!= null) && (tmp.right == node)) {
            node = tmp;
            tmp = tmp.parent;
        }
        
        return tmp;
    }

    /**
     * 左旋: 以当前节点
     * @param node
     */
    private void leftRotate(Node node) {
        if (node != null) {
            Node r = node.right;
            Node p = node.parent;

            node.right = r.left;
            if (r.left != null) {
                r.left.parent = node;
            }

            node.parent = r;
            r.left = node;

            r.parent = p;
            if (p.left == node) {
                p.left = r;
            } else {
                p.right = r;
            }
        }
//        return node;
    }

    /*右旋*/
    private void rightRotate(Node node) {
        if (node != null) {
            Node l = node.left;
            Node p = node.parent;

            node.left = l.right;
            if (l.right != null) {
                l.right.parent = node;
            }

            node.parent = l;
            l.right = node;

            l.parent = p;
            if (p.left == node) {
                p.left = l;
            } else {
                p.right = l;
            }
        }
//        return node;
    }

    private void setBlack(Node node) {
        if (node!= null) {
            node.color = BLACK;
        }
    }

    private void setRed(Node node) {
        if (node!= null) {
            node.color = RED;
        }
    }

    static class Node {
        int value;
        int color;
        Node left;
        Node right;
        Node parent;

        public Node() {
        }

        public Node(int value, int color) {
            this.value = value;
            this.color = color;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }


    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree(5);
        tree.insert(4);
        tree.insert(8);
        tree.insert(7);
        tree.insert(9);
        tree.insert(3);
        tree.insert(2);

        tree.delete(5);
        System.out.println(tree);
    }
}
