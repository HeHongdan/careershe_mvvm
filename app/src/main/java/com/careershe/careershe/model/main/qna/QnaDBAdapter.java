package com.careershe.careershe.model.main.qna;

import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

import com.blankj.utilcode.util.LogUtils;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.ItemRvDbQnaBinding;
import com.careershe.careershe.model.main.MainActivity;
import com.careershe.careershe.model.main.qna.bean.AnswerBean;
import com.careershe.careershe.model.main.qna.bean.QnaBean;
import com.careershe.ui.screenmatch.utils.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 3/4/21
 * @since v3/4/21
 */
public class QnaDBAdapter extends BaseQuickAdapter<QnaBean, BaseDataBindingHolder<ItemRvDbQnaBinding>> implements LoadMoreModule {

	/** 限制标签的总字数上限。 */
	private final int TAGS_MAX_COUNT = 18;

//	@BindingAdapter(value={"imageUrl", "placeholder"}, requireAll=false)
//	public static void setImageUrl(ImageView imageView, String url, Drawable placeHolder) {
//		if (url == null) {
//			imageView.setImageDrawable(placeholder);
//		} else {
//			MyImageLoader.loadInto(imageView, url, placeholder);
//		}
//	}

	public QnaDBAdapter() {
		super(R.layout.item_rv_db_qna);
	}


	@Override
	protected void convert(@NotNull BaseDataBindingHolder<ItemRvDbQnaBinding> holder, QnaBean item) {
		ItemRvDbQnaBinding binding = holder.getDataBinding();
		if (binding != null){
			binding.setQna(item);
//			binding.setPresenter(mPresenter);
			binding.executePendingBindings();
		}
	}
//	private MoviePresenter mPresenter = new MoviePresenter();



	/**
	 * 获取宿主Activity。
	 *
	 * @return ProfessionMainActivity__。
	 */
	private MainActivity getMainActivity() {
		if (getContext() instanceof MainActivity) {
			return (MainActivity) getContext();
		} else {
			return null;
		}
	}
}
