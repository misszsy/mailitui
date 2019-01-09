Vue.component("icon-list",{
    template:`<el-tabs type="border-card"> 
                <el-tab-pane label="官方图标">
                    <ul class="icon-list">
                        <li @dblclick="dblclick"  v-for="icon in iconList" v-if="icon.type==0"  :class="icon.name === name ? 'active' : 'default' ">
                             <span>
                                <i :class="icon.name"></i>
                                <span class="icon-name">{{icon.name}}</span>
                             </span>
                         </li>
                    </ul>
                </el-tab-pane>
                <el-tab-pane label="第三方图标">
                    <ul class="icon-list">
                        <li  @dblclick="dblclick" v-for="icon in iconList" v-if="icon.type==1"  :class="icon.name === name ? 'active' : 'default' ">
                             <span>
                                <i :class="icon.name"></i>
                                <span class="icon-name">{{icon.name}}</span>
                             </span>
                         </li>    
                    </ul>
                </el-tab-pane>
               </el-tabs>`,
    props: {
        list:Array,
        name:String,
    },
    data(){
        return {
            iconList:[],
            iconName:'',
        }
    },
    mounted() {
        this.iconList=this.list;
    },
    methods:{
        dblclick(e){
            this.$emit('dblclick',e);
        }
    }
})

Vue.component("el-editor",{
    template:`<div>
                    <script id="editor" type="text/plain"></script>
              </div>
            `,
    props: {
        content: String,
        contentStyle:String
    },
    data(){
        return {
            editor:null,
            config: {
                initialContent:'请输入内容',   //初始化编辑器的内容,也可以通过textarea/script给值，看官网例子
                initialFrameWidth: 800,
                initialFrameHeight: 450,
            },
        }
    },
    mounted() {
        const _this = this;
        _this.editor = UE.getEditor("editor",_this.config)
        _this.editor.addListener("ready", () => {
            _this.editor.setContent(_this.content || '')
        })
    },
    methods:{
       getUEContent() { // 获取内容方法
           return this.editor.getContent()
       }
    },
    destroy(){
        this.editor.destroy();
    }
})