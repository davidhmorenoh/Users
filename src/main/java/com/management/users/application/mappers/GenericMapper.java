package com.management.users.application.mappers;

public interface GenericMapper<RequestDto, ResponseDto, Entity> {

    ResponseDto toDto(Entity entity);

    Entity toEntity(RequestDto requestDto);

}