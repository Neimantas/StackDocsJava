package Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import Models.Business.CrudUpdate;
import Models.Business.Example;
import Models.Business.Language;
import Models.Business.Topic;
import Models.Const.Error;
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
import Services.ICrud;
import Services.IHigherService;

public class HigherServiceImpl implements IHigherService {

	private ICrud _crud;

	public HigherServiceImpl(CRUD crudImpl) {
		_crud = crudImpl;
	}

	public TopicsDTO getTopicsByLanguageId(int languageId) {
		ReadTableDTO dto = _crud.read(new TopicsDAL());
		if (dto.success) {
			List<TopicsDAL> list = (List<TopicsDAL>) (Object) dto.readResultSet;
			List<TopicsDAL> ret = list.stream().filter(e -> e.languageId == languageId).collect(Collectors.toList());
			return new TopicsDTO(true, ret, "success");
		}
		return new TopicsDTO(false, null, dto.message);
	}

	@Override
	public ExamplesDTO getExamplesByTopicId(int topicId) {
		ReadTableDTO examplesDTO = _crud.read(new ExamplesDAL());
		if (examplesDTO.success) {
			List<ExamplesDAL> list = (List<ExamplesDAL>) (Object) examplesDTO.readResultSet;
			List<ExamplesDAL> ret = list.stream().filter(e -> e.topicId == topicId).collect(Collectors.toList());
			return new ExamplesDTO(true, ret, "success");
		}
		return new ExamplesDTO(false, null, examplesDTO.message);
	}

	@Override
	public LanguageTagDTO getAllLanguages() {
		ReadTableDTO languageTagsDTO = _crud.read(new LanguageTagsDAL());
		if (languageTagsDTO.success) {
			List<LanguageTagsDAL> list = (List<LanguageTagsDAL>) (Object) languageTagsDTO.readResultSet;
			LanguageTagDTO ret = new LanguageTagDTO();
			ret.success = true;
			ret.languageTag = list;
			ret.message = "success";
			return ret;
		}
		LanguageTagDTO ret = new LanguageTagDTO();
		ret.success = false;
		ret.message = languageTagsDTO.message;
		return ret;
	}

	@Override
	public TopicsDTO getAllTopics() {
		ReadTableDTO topicsDTO = _crud.read(new TopicsDAL());
		if (topicsDTO.success) {
			List<TopicsDAL> list = (List<TopicsDAL>) (Object) topicsDTO.readResultSet;
			TopicsDTO ret = new TopicsDTO();
			ret.success = true;
			ret.message = "success";
			ret.topics = list;
			return ret;
		}
		TopicsDTO ret = new TopicsDTO();
		ret.success = false;
		ret.message = topicsDTO.message;
		return ret;
	}

	public ExamplesDTO getAllExamples() {
		ReadTableDTO examplesDTO = _crud.read(new ExamplesDAL());
		if (examplesDTO.success) {
			List<ExamplesDAL> list = (List<ExamplesDAL>) (Object) examplesDTO.readResultSet;
			ExamplesDTO ret = new ExamplesDTO();
			ret.success = true;
			ret.message = "success";
			ret.examples = list;
			return ret;
		}
		ExamplesDTO ret = new ExamplesDTO();
		ret.success = false;
		ret.message = examplesDTO.message;
		return ret;
	}

	@Override
	public TopicsDTO getTopicInfoByTopicId(int topicId) {
		ReadTableDTO topicsDTO = _crud.read(new TopicsDAL());
		if (topicsDTO.success) {
			List<TopicsDAL> list = (List<TopicsDAL>) (Object) topicsDTO.readResultSet;
			List<TopicsDAL> ret = list.stream().filter(e -> e.topicId == topicId).collect(Collectors.toList());
			return new TopicsDTO(true, ret, "success");
		}
		return new TopicsDTO(false, null, topicsDTO.message);
	}

	@Override
	public ExamplesDTO getExampleByExampleId(int exampleId) {
		ReadTableDTO examplesDTO = _crud.read(new ExamplesDAL());
		if (examplesDTO.success) {
			List<ExamplesDAL> list = (List<ExamplesDAL>) (Object) examplesDTO.readResultSet;
			List<ExamplesDAL> ret = list.stream().filter(e -> e.exampleId == exampleId).collect(Collectors.toList());
			return new ExamplesDTO(true, ret, "success");
		}
		return new ExamplesDTO(false, null, examplesDTO.message);
	}

	@Override
	public LanguageTagDTO getLanguageTagByLanguageId(int languageId) {
		ReadTableDTO languageTagsDTO = _crud.read(new LanguageTagsDAL());
		if (languageTagsDTO.success) {
			List<LanguageTagsDAL> list = (List<LanguageTagsDAL>) (Object) languageTagsDTO.readResultSet;
			List<LanguageTagsDAL> ret = list.stream().filter(e -> e.languageId == languageId)
					.collect(Collectors.toList());
			return new LanguageTagDTO(true, ret, "success");
		}
		return new LanguageTagDTO(false, null, languageTagsDTO.message);
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
		ret.success = false;
		ret.message = Error.CREATE_ERROR.getMessage();
		return ret;
	}

	private CreateTableDTO createTopic(Topic topic) {
		TopicsDAL dal = new TopicsDAL();
		CreateTableDTO ret = new CreateTableDTO();
		TopicsDTO tdto = getAllTopics();
		if (!tdto.success) {
			ret.success = false;
			ret.message = tdto.message;
			return ret;
		}
		int newTopicId = findFirstEmptyId(tdto);
		dal.topicId = newTopicId;
		dal.introductionHtml = topic.introductionHtml;
		dal.isHelloWorldTopic = 0;
		dal.languageId = topic.languageId;
		dal.parametersHtml = topic.parametersHtml;
		dal.remarksHtml = topic.remarksHtml;
		dal.syntaxHtml = topic.syntaxHtml;
		dal.title = topic.topicTitle;
		return _crud.create(dal);
	}

	private CreateTableDTO createExample(Example example) {
		ExamplesDAL dal = new ExamplesDAL();
		CreateTableDTO ret = new CreateTableDTO();
		ExamplesDTO edto = getAllExamples();
		if (!edto.success) {
			ret.success = false;
			ret.message = edto.message;
			return ret;
		}
		int newExampleId = edto.examples.size() + Settings.defaultNumberForExamplesIds;
		dal.exampleId = newExampleId;
		dal.bodyHtml = example.bodyHtml;
		dal.title = example.title;
		dal.topicId = example.topicId;
		return _crud.create(dal);
	}

	private CreateTableDTO createLanguage(Language language) {
		LanguageTagsDAL dal = new LanguageTagsDAL();
		CreateTableDTO ret = new CreateTableDTO();
		LanguageTagDTO ldto = getAllLanguages();
		if (!ldto.success) {
			ret.success = false;
			ret.message = ldto.message;
			return ret;
		}
		int newLanguageId = ldto.languageTag.size() + Settings.defaultNumberForLanguageIds;
		dal.languageId = newLanguageId;
		dal.helloWorldTopicId = -1; // not used field
		dal.tag = language.tag;
		dal.title = language.title;
		return _crud.create(dal);
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
		updateTableTDO.success = false;
		updateTableTDO.message = Error.UPDATE_ERROR.getMessage();
		return updateTableTDO;
	}

	private UpdateTableDTO updateLanguages(Language language) {
		CrudUpdate params = new CrudUpdate();
		UpdateTableDTO crudCheck;
		params.whereUsed = true;
		params.tableName = "LanguageTags";
		params.conditionColumName = "LanguageID";
		params.conditionChangeWhereValueIsEqual = Integer.toString(language.id);
		params.changeValueOfColum = "Title";
		params.changeValueTO = language.title;
		crudCheck = _crud.update(params);
		UpdateTableDTO updateTableTDO = new UpdateTableDTO();
		if (crudCheck.success) {
			updateTableTDO.success = true;
			updateTableTDO.message = crudCheck.message;
			return updateTableTDO;
		} else {
			updateTableTDO.success = false;
			updateTableTDO.message = crudCheck.message;
			return updateTableTDO;
		}
	}

	private UpdateTableDTO updateExamples(Example example) {
		CrudUpdate params = new CrudUpdate();
		UpdateTableDTO crudCheck;
		params.whereUsed = true;
		params.tableName = "Examples";
		params.conditionColumName = "ExampleId";
		params.conditionChangeWhereValueIsEqual = Integer.toString(example.exampleId);
		params.changeValueOfColum = "topicId";
		params.changeValueTO = Integer.toString(example.topicId);
		crudCheck = _crud.update(params);

		params.changeValueOfColum = "title";
		params.changeValueTO = example.title;
		crudCheck = _crud.update(params);

		params.changeValueOfColum = "bodyHtml";
		params.changeValueTO = example.bodyHtml;
		crudCheck = _crud.update(params);

		UpdateTableDTO updateTableTDO = new UpdateTableDTO();
		if (crudCheck.success) {
			updateTableTDO.success = true;
			updateTableTDO.message = crudCheck.message;
			return updateTableTDO;
		} else {
			updateTableTDO.success = false;
			updateTableTDO.message = crudCheck.message;
			return updateTableTDO;
		}
	}

	private UpdateTableDTO updateTopic(Topic topic) {
		CrudUpdate params = new CrudUpdate();
		UpdateTableDTO crudCheck;
		params.whereUsed = true;
		params.tableName = "Topics";
		params.conditionColumName = "TopicId";
		params.conditionChangeWhereValueIsEqual = Integer.toString(topic.topicId);

		params.changeValueOfColum = "Title";
		params.changeValueTO = topic.topicTitle;
		crudCheck = _crud.update(params);

		params.changeValueOfColum = "IntroductionHtml";
		params.changeValueTO = topic.introductionHtml;
		crudCheck = _crud.update(params);

		params.changeValueOfColum = "SyntaxHtml";
		params.changeValueTO = topic.syntaxHtml;
		crudCheck = _crud.update(params);

		params.changeValueOfColum = "ParametersHtml";
		params.changeValueTO = topic.parametersHtml;
		crudCheck = _crud.update(params);

		params.changeValueOfColum = "RemarksHtml";
		params.changeValueTO = topic.remarksHtml;
		crudCheck = _crud.update(params);

		UpdateTableDTO updateTableTDO = new UpdateTableDTO();
		if (crudCheck.success) {

			updateTableTDO.success = true;
			updateTableTDO.message = crudCheck.message;
			return updateTableTDO;
		} else {

			updateTableTDO.success = false;
			updateTableTDO.message = crudCheck.message;
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
		ret.success = false;
		ret.message = Error.DELETE_ERROR.getMessage();
		return ret;
	}

	private DeleteTableDTO deleteLanguage(Language deleteRecord) {
		return _crud.delete("LanguageTags", "languageid", Integer.toString(deleteRecord.id));
	}

	private DeleteTableDTO deleteExample(Example deleteRecord) {
		return _crud.delete("Examples", "exampleid", Integer.toString(deleteRecord.exampleId));
	}

	private DeleteTableDTO deleteTopic(Topic deleteRecord) {
		return _crud.delete("Topics", "topicid", Integer.toString(deleteRecord.topicId));
	}

	private int findFirstEmptyId(TopicsDTO tdto) {
		int emptyId = Settings.defaultFirstEmptyId;
		for (int i = 0; i < tdto.topics.size(); i++) {
			if (tdto.topics.get(i).topicId == emptyId) {
				emptyId++;
			} else if (tdto.topics.get(i).topicId > emptyId) {
				break;
			}
		}
		return emptyId;
	}

}