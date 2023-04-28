package com.example.springredditclone.mapper;

import com.example.springredditclone.dto.VoteResponse;
import com.example.springredditclone.model.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface VoteMapper {
    VoteResponse toDTO(Vote vote);
}
