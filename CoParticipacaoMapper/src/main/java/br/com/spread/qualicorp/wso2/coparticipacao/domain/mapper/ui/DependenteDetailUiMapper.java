package br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.ui;

import org.mapstruct.Mapper;

import br.com.spread.qualicorp.wso2.coparticipacao.domain.DependenteDetail;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.ui.DependenteDetailUi;
import br.com.spread.qualicorp.wso2.coparticipacao.domain.mapper.AbstractMapper;

/**
 * 
 * @author <a href="mailto:lotalava@gmail.com">Edson Alves Pereira</a>
 *
 */
@Mapper(
		componentModel = "spring",
		uses = { UserUiMapper.class, DependenteUiMapper.class, ArquivoInputColsDefUiMapper.class })
public abstract class DependenteDetailUiMapper extends AbstractMapper<DependenteDetail, DependenteDetailUi> {

}
