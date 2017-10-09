package org.geilove.response;

import org.geilove.pojo.ItemPgComment;
import org.geilove.pojo.ItemProgress;
import java.util.List;

/**
 * Created by mfhj-dz-001-424 on 17/2/7.
 */
public class ProgressUpdate {

    private ItemProgress itemProgress;  //进度更新
    private List<ItemPgComment> lmp;  //进度更新的评论回复列表

    public ItemProgress getItemProgress() {
        return itemProgress;
    }

    public void setItemProgress(ItemProgress itemProgress) {
        this.itemProgress = itemProgress;
    }

    public List<ItemPgComment> getLmp() {
        return lmp;
    }

    public void setLmp(List<ItemPgComment> lmp) {
        this.lmp = lmp;
    }
}
