package com.careershe.careershe.model.main.task;


import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.careershe.careershe.R;
import com.careershe.careershe.databinding.ItemRvSchoolBinding;

import com.careershe.ui.screenmatch.utils.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 类描述：。
 *
 * @author HeHongdan
 * @date 3/4/21
 * @since v3/4/21
 */
//public class SchoolAdapter extends BaseQuickAdapter<SchoolBean, BaseDataBindingHolder<ItemRvSchoolBinding>> {
	public class SchoolAdapter extends BaseQuickAdapter<SchoolBean,BaseDataBindingHolder<ItemRvSchoolBinding>> implements LoadMoreModule {


	public SchoolAdapter() {
		super(R.layout.item_rv_school);
	}

	@Override
	protected void convert(@NotNull BaseDataBindingHolder<ItemRvSchoolBinding> holder, SchoolBean item) {
		ItemRvSchoolBinding binding = holder.getDataBinding();
		Objects.requireNonNull(binding,"为空").setSchool(item);
		binding.executePendingBindings();



		holder.itemView.post(new Runnable() {
			@Override
			public void run() {
				int height = holder.itemView.getHeight();
				int width = holder.itemView.getWidth();

				LogUtils.d(
						"RV的高度= " + ConvertUtils.px2dp(getContext(), height)
								+ "；RV的宽度= " + ConvertUtils.px2dp(getContext(), width)
				);
			}
		});

	}

}
