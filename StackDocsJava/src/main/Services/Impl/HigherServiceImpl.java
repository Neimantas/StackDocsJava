package Services.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.internal.compiler.classfmt.MethodInfoWithAnnotations;

import Models.CrudUpdate;
import Models.Example;
import Models.Language;
import Models.Topic;
import Models.Const.Errors;
import Models.Const.Settings;
import Models.DAL.ExamplesDAL;
import Models.DAL.LanguageTagsDAL;
import Models.DAL.TopicsDAL;
import Models.DTO.CreateTableDTO;
import Models.DTO.DeleteTableDTO;
import Models.DTO.ExamplesDTO;
import Models.DTO.LanguageTagDTO;
import Models.DTO.ReadTableDTO;
import Models.DTO.TopicsDTO;
import Models.DTO.UpdateTableDTO;
import Services.ICache;
import Services.ICrud;
import Services.IHigherService;

public class HigherServiceImpl implements IHigherService {

	ICrud crud;

	public HigherServiceImpl(CRUD crudImpl) {
		crud = crudImpl;
	}

	public TopicsDTO getTopicsByLanguageId(int languageId) {
		ReadTableDTO dto = crud.read(new TopicsDAL());
		if (dto.isSuccess()) {
			List<TopicsDAL> list = (List<TopicsDAL>) (Object) dto.getReadResultSet();
			List<TopicsDAL> ret = list.stream().filter(e -> e.languageId == languageId)
					.collect(Collectors.toList());
			return new TopicsDTO(true, ret, "success");
		}
		return new TopicsDTO(false, null, dto.getMessage());
	}

	@Override
	public ExamplesDTO getExamplesByTopicId(int topicId) {
		ReadTableDTO examplesDTO = crud.read(new ExamplesDAL());
		if (examplesDTO.isSuccess()) {
			List<ExamplesDAL> list = (List<ExamplesDAL>) (Object) examplesDTO.getReadResultSet();
			List<ExamplesDAL> ret = list.stream().filter(e -> e.topicId == topicId).collect(Collectors.toList());
			return new ExamplesDTO(true, ret, "success");
		}
		return new ExamplesDTO(false, null, examplesDTO.getMessage());
	}

	@Override
	public LanguageTagDTO getAllLanguages() {
		ReadTableDTO languageTagsDTO = crud.read(new LanguageTagsDAL());
		if (languageTagsDTO.isSuccess()) {
			List<LanguageTagsDAL> list = (List<LanguageTagsDAL>) (Object) languageTagsDTO.getReadResultSet();
			LanguageTagDTO ret = new LanguageTagDTO();
			ret.setSuccess(true);
			ret.setLanguageTag(list);
			ret.setMessage("success");
			return ret;
		}
		LanguageTagDTO ret = new LanguageTagDTO();
		ret.setSuccess(false);
		ret.setMessage(languageTagsDTO.getMessage());
		return ret;
	}

	@Override
	public TopicsDTO getAllTopics() {
		ReadTableDTO topicsDTO = crud.read(new TopicsDAL());
		if (topicsDTO.isSuccess()) {
			List<TopicsDAL> list = (List<TopicsDAL>) (Object) topicsDTO.getReadResultSet();
			TopicsDTO ret = new TopicsDTO();
			ret.setSuccess(true);
			ret.setMessage("success");
			ret.setTopics(list);
			return ret;
		}
		TopicsDTO ret = new TopicsDTO();
		ret.setSuccess(false);
		ret.setMessage(topicsDTO.getMessage());
		return ret;
	}

	public ExamplesDTO getAllExamples() {
		ReadTableDTO examplesDTO = crud.read(new ExamplesDAL());
		if (examplesDTO.isSuccess()) {
			List<ExamplesDAL> list = (List<ExamplesDAL>) (Object) examplesDTO.getReadResultSet();
			ExamplesDTO ret = new ExamplesDTO();
			ret.setSuccess(true);
			ret.setMessage("success");
			ret.setExamples(list);
			return ret;
		}
		ExamplesDTO ret = new ExamplesDTO();
		ret.setSuccess(false);
		ret.setMessage(examplesDTO.getMessage());
		return ret;
	}

	@Override
	public TopicsDTO getTopicInfoByTopicId(int topicId) {
		ReadTableDTO topicsDTO = crud.read(new TopicsDAL());
		if (topicsDTO.isSuccess()) {
			List<TopicsDAL> list = (List<TopicsDAL>) (Object) topicsDTO.getReadResultSet();
			List<TopicsDAL> ret = list.stream().filter(e -> e.topicId == topicId)
					.collect(Collectors.toList());
			return new TopicsDTO(true, ret, "success");
		}
		return new TopicsDTO(false, null, topicsDTO.getMessage());
	}

	@Override
	public ExamplesDTO getExampleByExampleId(int exampleId) {
		ReadTableDTO examplesDTO = crud.read(new ExamplesDAL());
		if (examplesDTO.isSuccess()) {
			List<ExamplesDAL> list = (List<ExamplesDAL>) (Object) examplesDTO.getReadResultSet();
			List<ExamplesDAL> ret = list.stream().filter(e -> e.exampleId == exampleId)
					.collect(Collectors.toList());
			return new ExamplesDTO(true, ret, "success");
		}
		return new ExamplesDTO(false, null, examplesDTO.getMessage());
	}

	@Override
	public LanguageTagDTO getLanguageTagByLanguageId(int languageId) {
		ReadTableDTO languageTagsDTO = crud.read(new LanguageTagsDAL());
		if (languageTagsDTO.isSuccess()) {
			List<LanguageTagsDAL> list = (List<LanguageTagsDAL>) (Object) languageTagsDTO.getReadResultSet();
			List<LanguageTagsDAL> ret = list.stream().filter(e -> e.languageId == languageId)
					.collect(Collectors.toList());
			return new LanguageTagDTO(true, ret, "success");
		}
		return new LanguageTagDTO(false, null, languageTagsDTO.getMessage());
	}

	@Override
	public CreateTableDTO create(Object insertRecord) {
		if (insertRecord instanceof Topic) {
			return createTopic((Topic) insertRecord);
		} else if (insertRecord instanceof Example) {
			return createExample((Example) insertRecord);
		} else if (insertRecord instanceof Language) {
			return createLanguage((Language) insertRecord);
		}
		CreateTableDTO ret = new CreateTableDTO();
		ret.setSuccess(false);
		ret.setMessage(Errors.createError.getMessage());
		return ret;
	}

	private CreateTableDTO createTopic(Topic topic) {
		TopicsDAL dal = new TopicsDAL();
		CreateTableDTO ret = new CreateTableDTO();
		TopicsDTO tdto = getAllTopics();
		if (!tdto.isSuccess()) {
			ret.setSuccess(false);
			ret.setMessage(tdto.getMessage());
			return ret;
		}
		int newTopicId = findFirstEmptyId(tdto);
		dal.topicId = newTopicId;
		dal.introductionHtml = topic.get_IntroductionHtml();
		dal.isHelloWorldTopic = 0;
		dal.languageId = topic.get_LanguageId();
		dal.parametersHtml = topic.get_ParametersHtml();
		dal.remarksHtml = topic.get_RemarksHtml();
		dal.syntaxHtml = topic.get_SyntaxHtml();
		dal.title = topic.get_TopicTitle();
		return crud.create(dal);
	}

	private CreateTableDTO createExample(Example example) {
		ExamplesDAL dal = new ExamplesDAL();
		CreateTableDTO ret = new CreateTableDTO();
		ExamplesDTO edto = getAllExamples();
		if (!edto.isSuccess()) {
			ret.setSuccess(false);
			ret.setMessage(edto.getMessage());
			return ret;
		}
		int newExampleId = edto.getExamples().size() + Settings.defaultNumberForExamplesIds;
		dal.exampleId = newExampleId;
		dal.bodyHtml = example.bodyHtml;
		dal.title = example.title;
		dal.topicId = example.topicId;
		return crud.create(dal);
	}

	private CreateTableDTO createLanguage(Language language) {
		LanguageTagsDAL dal = new LanguageTagsDAL();
		CreateTableDTO ret = new CreateTableDTO();
		LanguageTagDTO ldto = getAllLanguages();
		if (!ldto.isSuccess()) {
			ret.setSuccess(false);
			ret.setMessage(ldto.getMessage());
			return ret;
		}
		int newLanguageId = ldto.getLanguageTag().size() + Settings.defaultNumberForLanguageIds;
		dal.languageId = newLanguageId;
		dal.helloWorldTopicId = -1; //not used field
		dal.tag = language.tag;
		dal.title = language.title;
		return crud.create(dal);
	}

	@Override
	public UpdateTableDTO update(Object updataRecord) {
		if (updataRecord instanceof Topic) {
			return updateTopic((Topic) updataRecord);
		} else if (updataRecord instanceof Example) {
			return updateExamples((Example) updataRecord);
		} else if (updataRecord instanceof Language) {
			return updateLanguages((Language) updataRecord);
		}
		UpdateTableDTO updateTableTDO = new UpdateTableDTO();
		updateTableTDO.setSuccess(false);
		updateTableTDO.setMessage(Errors.updateError.getMessage());
		return updateTableTDO;
	}

	private UpdateTableDTO updateLanguages(Language language) {
		CrudUpdate params = new CrudUpdate();
		UpdateTableDTO crudCheck;
		params.setWhereUsed(true);
		params.setTableName("LanguageTags"); 
		params.setConditionColumName("LanguageID"); 
		params.setConditionChangeWhereValueIsEqual(Integer.toString(language.id)); 
		params.setChangeValueOfColum("Title"); 
		params.setChangeValueTO(language.title);
		crudCheck = crud.update(params);
		UpdateTableDTO updateTableTDO = new UpdateTableDTO();
		if (crudCheck.isSuccess()) {
			updateTableTDO.setSuccess(true);
			updateTableTDO.setMessage(crudCheck.getMessage());
			return updateTableTDO;
		} else {
			updateTableTDO.setSuccess(false);
			updateTableTDO.setMessage(crudCheck.getMessage());
			return updateTableTDO;
		}
	}

	private UpdateTableDTO updateExamples(Example example) {
		CrudUpdate params = new CrudUpdate();
		UpdateTableDTO crudCheck;
		params.setWhereUsed(true);
		params.setTableName("Examples"); 
		params.setConditionColumName("ExampleId"); 
		params.setConditionChangeWhereValueIsEqual(Integer.toString(example.exampleId)); 
		params.setChangeValueOfColum("topicId"); 
		params.setChangeValueTO(Integer.toString(example.topicId)); 
		crudCheck = crud.update(params);

		params.setChangeValueOfColum("title"); 
		params.setChangeValueTO(example.title);
		crudCheck = crud.update(params);

		params.setChangeValueOfColum("bodyHtml"); 
		params.setChangeValueTO(example.bodyHtml); 
		crudCheck = crud.update(params);

		UpdateTableDTO updateTableTDO = new UpdateTableDTO();
		if (crudCheck.isSuccess()) {
			updateTableTDO.setSuccess(true);
			updateTableTDO.setMessage(crudCheck.getMessage());
			return updateTableTDO;
		} else {
			updateTableTDO.setSuccess(false);
			updateTableTDO.setMessage(crudCheck.getMessage());
			return updateTableTDO;
		}
	}

	private UpdateTableDTO updateTopic(Topic topic) {
		CrudUpdate params = new CrudUpdate();
		UpdateTableDTO crudCheck;
		params.setWhereUsed(true);
		params.setTableName("Topics"); 
		params.setConditionColumName("TopicId"); 
		params.setConditionChangeWhereValueIsEqual(Integer.toString(topic._TopicId)); 

		params.setChangeValueOfColum("Title"); 
		params.setChangeValueTO(topic._TopicTitle); 
		crudCheck = crud.update(params);

		params.setChangeValueOfColum("IntroductionHtml"); 
		params.setChangeValueTO(topic._IntroductionHtml); 
		crudCheck = crud.update(params);

		params.setChangeValueOfColum("SyntaxHtml"); 
		params.setChangeValueTO(topic._SyntaxHtml); 
		crudCheck = crud.update(params);

		params.setChangeValueOfColum("ParametersHtml"); 
		params.setChangeValueTO(topic._ParametersHtml); 
		crudCheck = crud.update(params);

		params.setChangeValueOfColum("RemarksHtml"); 
		params.setChangeValueTO(topic._RemarksHtml); 
		crudCheck = crud.update(params);

		UpdateTableDTO updateTableTDO = new UpdateTableDTO();
		if (crudCheck.isSuccess()) {

			updateTableTDO.setSuccess(true);
			updateTableTDO.setMessage(crudCheck.getMessage());
			return updateTableTDO;
		} else {

			updateTableTDO.setSuccess(false);
			updateTableTDO.setMessage(crudCheck.getMessage());
			return updateTableTDO;
		}
	}

	@Override
	public DeleteTableDTO delete(Object deleteRecord) {
		if (deleteRecord instanceof Topic) {
			return deleteTopic((Topic) deleteRecord);
		} else if (deleteRecord instanceof Example) {
			return deleteExample((Example) deleteRecord);
		} else if (deleteRecord instanceof Language) {
			return deleteLanguage((Language) deleteRecord);
		}
		DeleteTableDTO ret = new DeleteTableDTO();
		ret.setSuccess(false);
		ret.setMessage(Errors.deleteError.getMessage());
		return ret;
	}

	private DeleteTableDTO deleteLanguage(Language deleteRecord) {
		return crud.delete("LanguageTags", "languageid", Integer.toString(deleteRecord.id));
	}

	private DeleteTableDTO deleteExample(Example deleteRecord) {
		return crud.delete("Examples", "exampleid", Integer.toString(deleteRecord.exampleId));
	}

	private DeleteTableDTO deleteTopic(Topic deleteRecord) {
		return crud.delete("Topics", "topicid", Integer.toString(deleteRecord._TopicId));
	}

	private int findFirstEmptyId(TopicsDTO tdto) {
		int emptyId = Settings.defaultFirstEmptyId;
		for (int i = 0; i < tdto.getTopics().size(); i++) {
			if (tdto.getTopics().get(i).topicId == emptyId) {
				emptyId++;
			} else if (tdto.getTopics().get(i).topicId > emptyId) {
				break;
			}
		}
		return emptyId;
	}

}