package com.fanhehe.message.dao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

interface BaseDao<T> extends Mapper<T>, InsertListMapper<T> {
}
