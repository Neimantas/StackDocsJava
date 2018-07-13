package Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import Models.Business.CrudUpdate;
import Models.Business.Example;
import Models.Business.Language;
import Models.Business.Topic;
import Models.Const.Error;
import Models.Const.OperationStatus;
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
			return new TopicsDTO(true, ret, OperationStatus.success.getMessage());
		}
		return new TopicsDTO(false, null, dto.message);
	}

	@Override
	public ExamplesDTO getExamplesByTopicId(int topicId) {
		ReadTableDTO examplesDTO = _crud.read(new ExamplesDAL());
		if (examplesDTO.success) {
			List<ExamplesDAL> list = (List<ExamplesDAL>) (Object) examplesDTO.readResultSet;
			List<ExamplesDAL> ret = list.stream().filter(e -> e.topicId == topicId).collect(Collectors.toList());
			return new ExamplesDTO(true, ret, OperationStatus.success.getMessage());
		}
		return new ExamplesDTO(false, null, examplesDTO.message);
	}

	@Override
	public LanguageTagDTO getAllLanguages() {
		ReadTableDTO languageTagsDTO = _crud.read(new LanguageTagsDAL());
		if (languageTagsDTO.success) {
			List<LanguageTagsDAL> list = (List<LanguageTagsDAL>) (Object) languageTagsDTO.readResultSet;
			return new LanguageTagDTO(true, list, OperationStatus.success.getMessage());
		}
		return new LanguageTagDTO(false, null, languageTagsDTO.message);
	}

	@Override
	public TopicsDTO getAllTopics() {
		ReadTableDTO topicsDTO = _crud.read(new TopicsDAL());
		if (topicsDTO.success) {
			List<TopicsDAL> list = (List<TopicsDAL>) (Object) topicsDTO.readResultSet;
			return new TopicsDTO(true, list, OperationStatus.success.getMessage());
		}
		return new TopicsDTO(false, null, topicsDTO.message);
	}

	public ExamplesDTO getAllExamples() {
		ReadTableDTO examplesDTO = _crud.read(new ExamplesDAL());
		if (examplesDTO.success) {
			List<ExamplesDAL> list = (List<ExamplesDAL>) (Object) examplesDTO.readResultSet;
			return new ExamplesDTO(true, list, OperationStatus.success.getMessage());
		}
		return new ExamplesDTO(false, null, examplesDTO.message);
	}

	@Override
	public TopicsDTO getTopicInfoByTopicId(int topicId) {
		ReadTableDTO topicsDTO = _crud.read(new TopicsDAL());
		if (topicsDTO.success) {
			List<TopicsDAL> list = (List<TopicsDAL>) (Object) topicsDTO.readResultSet;
			List<TopicsDAL> ret = list.stream().filter(e -> e.topicId == topicId).collect(Collectors.toList());
			return new TopicsDTO(true, ret, OperationStatus.success.getMessage());
		}
		return new TopicsDTO(false, null, topicsDTO.message);
	}

	@Override
	public ExamplesDTO getExampleByExampleId(int exampleId) {
		ReadTableDTO examplesDTO = _crud.read(new ExamplesDAL());
		if (examplesDTO.success) {
			List<ExamplesDAL> list = (List<ExamplesDAL>) (Object) examplesDTO.readResultSet;
			List<ExamplesDAL> ret = list.stream().filter(e -> e.exampleId == exampleId).collect(Collectors.toList());
			return new ExamplesDTO(true, ret, OperationStatus.success.getMessage());
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
			return new LanguageTagDTO(true, ret, OperationStatus.success.getMessage());
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
		return new CreateTableDTO(false, Error.CREATE_ERROR.getMessage());
	}

	private CreateTableDTO createTopic(Topic topic) {
		TopicsDAL dal = new TopicsDAL();
		TopicsDTO tdto = getAllTopics();
		if (!tdto.success) {
			return new CreateTableDTO(false, tdto.message);
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
		ExamplesDTO edto = getAllExamples();
		if (!edto.success) {
			return new CreateTableDTO(false, edto.message);
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
		LanguageTagDTO ldto = getAllLanguages();
		if (!ldto.success) {
			return new CreateTableDTO(false, ldto.message);
		}
		int newLanguageId = ldto.languageTag.size() + Settings.defaultNumberForLanguageIds;
		dal.languageId = newLanguageId;
		dal.helloWorldTopicId = -1; // this db field is not used 
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
		return new UpdateTableDTO(false, Error.UPDATE_ERROR.getMessage());
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
		if (crudCheck.success) {
			return new UpdateTableDTO(true, crudCheck.message);
		} else {
			return new  UpdateTableDTO(false, crudCheck.message);
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

		if (crudCheck.success) {
			return new UpdateTableDTO(true, crudCheck.message);
		} else {
			return new UpdateTableDTO(false, crudCheck.message);
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

		if (crudCheck.success) {
			return new UpdateTableDTO(true, crudCheck.message);
		} else {
			return new UpdateTableDTO(false, crudCheck.message);
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
		return new DeleteTableDTO(false, Error.DELETE_ERROR.getMessage());
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