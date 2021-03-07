package com.careershe.careershe.model.main.qna;

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

	public void click(QnaBean item) {
		LogUtils.d("点击列表= "+ item);
	}

	public QnaDBAdapter() {
		super(R.layout.item_rv_db_qna);
	}


	//	private MoviePresenter mPresenter = new MoviePresenter();
	@Override
	protected void convert(@NotNull BaseDataBindingHolder<ItemRvDbQnaBinding> holder, QnaBean item) {
		ItemRvDbQnaBinding binding = holder.getDataBinding();
		if (binding != null){
			binding.setQna(item);
//			binding.setPresenter(mPresenter);
			binding.executePendingBindings();
		}

		ImageView ic_avatar = holder.getView(R.id.riv_questionUser);
//		Picasso.get().load(data.getImage()).placeholder(R.mipmap.ic_avatar).into(ic_avatar);

		AnswerBean answer = item.getAnswerVo();
		if (answer != null) {
			TextView tv_answer = holder.getView(R.id.tv_answer);
			String userAnswer = answer.getAnswerName() + ": " + answer.getAnswerText();
			Spannable spannable = new SpannableString(userAnswer);
			spannable.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.official)),
					0, answer.getAnswerName().length()+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			spannable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD),
					0, answer.getAnswerName().length()+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			tv_answer.setText(spannable);
			holder.getView(R.id.tv_answer).setVisibility(View.VISIBLE);
		} else {
			holder.getView(R.id.tv_answer).setVisibility(View.GONE);
		}

		if (!TextUtils.isEmpty(item.getKeywordName())) {
			setTag(holder,item);
		}

		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LogUtils.d("点击列表= "+ item.getId());
			}
		});
	}



	/**
	 * 设置标签。
	 *
	 * @param holder item视图。
	 * @param data item数据。
	 */
	private void setTag(@NotNull final BaseViewHolder holder, final QnaBean data) {
		LinearLayout rv_tag = holder.getView(R.id.ll_tag);
		if (rv_tag.getChildCount() > 0) {
			LogUtils.d("标签，子视图个数= " + rv_tag.getChildCount());
			rv_tag.removeAllViews();
		}

		List<String> keywords = new ArrayList<>();
		keywords.add(data.getKeywordName());
		int textCount = 0;
		try {
			for (int i = 0; i < keywords.size(); i++) {
				textCount = textCount + keywords.get(i).length();
				LogUtils.d("标签长度= " + textCount);
				String tag = keywords.get(i);
				if (textCount < TAGS_MAX_COUNT && !TextUtils.isEmpty(tag)) {
					LinearLayout ll_tag = holder.getView(R.id.ll_tag);
//						ConstraintLayout cl_tag = (ConstraintLayout) LayoutInflater.from(getContext()).inflate(R.layout.dev2_rv_item_qna_tag, null);
					ConstraintLayout cl_tag = (ConstraintLayout) LayoutInflater.from(getContext()).inflate(R.layout.dev2_rv_item_qna_tag, ll_tag, false);
					((TextView) cl_tag.findViewById(R.id.tv_tag)).setText(tag);
					ll_tag.addView(cl_tag);
					if (i > 0) {
						LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) cl_tag.getLayoutParams();
						lp.leftMargin = ConvertUtils.dp2px(getContext(), 4);
						cl_tag.setLayoutParams(lp);
					}
				}
			}
		} catch (Exception e) {
			LogUtils.e("设置(职业)标签失败= " + e);
		}
	}


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
