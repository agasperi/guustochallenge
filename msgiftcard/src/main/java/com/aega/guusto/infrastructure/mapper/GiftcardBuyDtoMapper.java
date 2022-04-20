package com.aega.guusto.infrastructure.mapper;

import com.aega.guusto.application.command.GiftcardBuyCommand;
import com.aega.guusto.application.command.GiftcardBuyReturn;
import com.aega.guusto.infrastructure.dto.GiftcardBuyRequest;
import com.aega.guusto.infrastructure.dto.GiftcardBuyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import reactor.core.publisher.Flux;


@Mapper
public abstract class GiftcardBuyDtoMapper {

    public static final GiftcardBuyDtoMapper INSTANCE = Mappers.getMapper(GiftcardBuyDtoMapper.class);

    public abstract GiftcardBuyCommand fromRequestToCommand(GiftcardBuyRequest request);

    public abstract GiftcardBuyResponse fromReturnToResponse(GiftcardBuyReturn commandReturn);

    public Flux<GiftcardBuyCommand> fluxFromRequestToCommand(Flux<GiftcardBuyRequest> request) {
        if (request == null) {
            return null;
        }

        return Flux.from(request.map(g -> fromRequestToCommand(g)));
    }

}
