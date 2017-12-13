package cn.aiyangkeji.bean;

/**
 * Created by chenzhikai on 2017/11/3.
 */



        import java.util.List;

public class Event
{
    /** 列表加载事件 */
    public static class ItemListEvent
    {
        private List<Item> items;

        public ItemListEvent(List<Item> items)
        {
            this.items = items;
        }

        public List<Item> getItems()
        {
            return items;
        }
    }

}
