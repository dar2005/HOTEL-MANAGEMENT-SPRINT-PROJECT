package com.cg.service;

import java.util.List;

import com.cg.entity.RoomType;

public interface RoomTypeService {

    List<RoomType> getAllTypes();

    RoomType getById(Long id);

    RoomType getByName(String name);

    RoomType create(RoomType rt);
}