package Services.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.internal.compiler.classfmt.MethodInfoWithAnnotations;

import Models.Business.CrudUpdate;
import Models.Business.Example;
import Models.Business.Language;
import Models.Business.Topic;
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

	// public HigherServiceImpl() {
	// crud = new CRUD();
	// }

	// private LanguageTagDTO readLanguageTag() {
	//
	// ICache cache = CacheImpl.getInstance();
	// if (cache.get("LanguageTag") != null) {
	// return (LanguageTagDTO)cache.get("LanguageTag");
	// }
	//
	// ICrud crud = new CRUD();
	//
	// ReadTableDTO readTableDTO = crud.read(new LanguageTagsDAL());
	// if(readTableDTO.isSuccess()) {
	// List<LanguageTagsDAL> languageList = new ArrayList<>();
	// ResultSet resultSet = readTableDTO.getReadResultSet();
	// try {
	//
	// while(resultSet.next()) {
	// LanguageTagsDAL languageTag = new LanguageTagsDAL();
	// languageTag.languageId = resultSet.getInt("LanguageId");
	// languageTag.tag = resultSet.getInt("Tag");
	// languageTag.title = resultSet.getString("Title");
	// languageTag.helloWorldTopicId = resultSet.getByte("HelloWorldTopicid");
	//
	// languageList.add(languageTag);
	// }
	// LanguageTagDTO ret = new LanguageTagDTO(true, languageList, "success");
	// cache.put("LanguageTag", ret);
	// return ret;
	//
	//
	// } catch (SQLException e) {
	//
	// return new LanguageTagDTO(false, null, e.getMessage());
	//
	// }
	// finally {
	// DataBaseImpl.getInstance().close();
	//
	// }
	// }
	//
	// return new LanguageTagDTO(false, null, readTableDTO.getMessage());
	//
	// }
	// private TopicsDTO readTopics() {
	//
	// ICache cache = CacheImpl.getInstance();
	// if (cache.get("Topics") != null) {
	// return (TopicsDTO)cache.get("Topics");
	// }
	//
	// ICrud crud = new CRUD();
	//
	// ReadTableDTO readTableDTO = crud.read(new TopicsDAL());
	// if(readTableDTO.isSuccess()) {
	// ResultSet resultSet = readTableDTO.getReadResultSet();
	// List<TopicsDAL> topics = new ArrayList<>();
	//
	// try {
	// while(resultSet.next()) {
	//
	// TopicsDAL topicsDal = new TopicsDAL();
	//
	// topicsDal.topicId = resultSet.getInt("TopicId");
	// topicsDal.languageId = resultSet.getInt("LanguageId");
	// topicsDal.title = resultSet.getString("Title");
	// topicsDal.isHelloWorldTopic = resultSet.getByte("IsHelloWorldTopic");
	// topicsDal.introductionHtml = resultSet.getString("IntroductionHtml");
	// topicsDal.syntaxHtml = resultSet.getString("SyntaxHtml");
	// topicsDal.parametersHtml = resultSet.getString("ParametersHtml");
	// topicsDal.remarksHtml = resultSet.getString("RemarksHtml");
	//
	// topics.add(topicsDal);
	//
	// }
	// TopicsDTO ret = new TopicsDTO(true, topics, "success");
	// cache.put("Topics", ret);
	// return ret;
	//
	// } catch (SQLException e) {
	//
	// return new TopicsDTO(false, null, e.getMessage());
	//
	// }
	// finally {
	// DataBaseImpl.getInstance().close();
	//
	// }
	// }
	//
	//
	//
	// return new TopicsDTO(false, null, readTableDTO.getMessage());
	// }
	// private ExamplesDTO readExamples() {
	//
	//
	//// ICrud crud = new CRUD();
	//
	// ReadTableDTO readTableDTO = crud.read(new ExamplesDAL());
	//
	// if(readTableDTO.isSuccess()) {
	// ResultSet resultSet = readTableDTO.getReadResultSet();
	// List<ExamplesDAL> examples = new ArrayList<>();
	//
	// try {
	// while(resultSet.next()) {
	//
	// ExamplesDAL example = new ExamplesDAL();
	// example.exampleId = resultSet.getInt("ExampleId");
	// example.topicId = resultSet.getInt("TopicId");
	// example.title = resultSet.getString("Title");
	// example.bodyHtml = resultSet.getString("BodyHtml");
	//
	// examples.add(example);
	// }
	//
	// return new ExamplesDTO(true, examples, "success");
	// } catch (SQLException e) {
	//
	// return new ExamplesDTO(false, null, e.getMessage());
	// }
	// finally {
	// DataBaseImpl.getInstance().close();
	//
	// }
	// }
	//
	// return new ExamplesDTO(false, null, readTableDTO.getMessage());
	// }
	public TopicsDTO getTopicsByLanguageId(int languageId) {

		// TopicsDTO topicsDTO = readTopics();
		// ICrud crud = new CRUD();
		ReadTableDTO dto = crud.read(new TopicsDAL());
		// check if readTopics method ended with errors
		if (dto.isSuccess()) {
			List<TopicsDAL> list = (List<TopicsDAL>) (Object) dto.getReadResultSet();
			List<TopicsDAL> ret = list.stream().filter(e -> e.languageId == languageId).collect(Collectors.toList());
			// add to cache before return
			return new TopicsDTO(true, ret, "success");
		}

		// List<Object> ret = topics.stream().filter(e -> ((TopicsDAL)e).languageId ==
		// languageId).collect(Collectors.toList());

		return new TopicsDTO(false, null, dto.getMessage());
	}

	@Override
	public ExamplesDTO getExamplesByTopicId(int topicId) {

		// ExamplesDTO examplesDTO = readExamples();
		// ICrud crud = new CRUD();
		ReadTableDTO examplesDTO = crud.read(new ExamplesDAL());
		// check if readTopics method ended with errors
		if (examplesDTO.isSuccess()) {
			List<ExamplesDAL> list = (List<ExamplesDAL>) (Object) examplesDTO.getReadResultSet();
			List<ExamplesDAL> ret = list.stream().filter(e -> e.topicId == topicId).collect(Collectors.toList());
			return new ExamplesDTO(true, ret, "success");
		}

		// List<Object> ret = examples.stream().filter(e -> ((ExamplesDAL)e).topicId ==
		// topicId).collect(Collectors.toList());
		return new ExamplesDTO(false, null, examplesDTO.getMessage());
	}

	@Override
	public LanguageTagDTO getAllLanguages() {
		// ICrud crud = new CRUD();
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
		// ICrud crud = new CRUD();
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

		// TopicsDTO topicsDTO = readTopics();
		// ICrud crud = new CRUD();
		ReadTableDTO topicsDTO = crud.read(new TopicsDAL());
		if (topicsDTO.isSuccess()) {

			List<TopicsDAL> list = (List<TopicsDAL>) (Object) topicsDTO.getReadResultSet();
			List<TopicsDAL> ret = list.stream().filter(e -> e.topicId == topicId).collect(Collectors.toList());

			return new TopicsDTO(true, ret, "success");
		}

		// List<Object> ret = topics.stream().filter(e -> ((TopicsDAL)e).languageId ==
		// languageId).collect(Collectors.toList());

		return new TopicsDTO(false, null, topicsDTO.getMessage());

	}

	@Override
	public ExamplesDTO getExampleByExampleId(int exampleId) {

		// ExamplesDTO examplesDTO = readExamples();
		// ICrud crud = new CRUD();
		ReadTableDTO examplesDTO = crud.read(new ExamplesDAL());
		if (examplesDTO.isSuccess()) {
			List<ExamplesDAL> list = (List<ExamplesDAL>) (Object) examplesDTO.getReadResultSet();
			List<ExamplesDAL> ret = list.stream().filter(e -> e.exampleId == exampleId).collect(Collectors.toList());
			return new ExamplesDTO(true, ret, "success");
		}

		return new ExamplesDTO(false, null, examplesDTO.getMessage());

	}

	@Override
	public LanguageTagDTO getLanguageTagByLanguageId(int languageId) {

		// LanguageTagDTO languageTagDTO = readLanguageTag();
		// ICrud crud = new CRUD();
		ReadTableDTO languageTagsDTO = crud.read(new LanguageTagsDAL());
		if (languageTagsDTO.isSuccess()) {
			List<LanguageTagsDAL> list = (List<LanguageTagsDAL>) (Object) languageTagsDTO.getReadResultSet();
			List<LanguageTagsDAL> ret = list.stream().filter(e -> e.languageId == languageId)
					.collect(Collectors.toList());

			return new LanguageTagDTO(true, ret, "success");
		}

		// List<Object> ret = topics.stream().filter(e -> ((TopicsDAL)e).languageId ==
		// languageId).collect(Collectors.toList());

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
		ret.setMessage(
				"ERROR: wrong parameter given! Create method accepts only instances of Topic, Example and Language classes");

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
		int newExampleId = edto.getExamples().size() + 10000;

		System.out.println(newExampleId);

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
		int newLanguageId = ldto.getLanguageTag().size() + 60;

		System.out.println(newLanguageId);

		dal.languageId = newLanguageId;
		dal.helloWorldTopicId = -1;
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
		updateTableTDO.setMessage(
				"ERROR: wrong parameter given! Update method accepts only instances of Topic, Example and Language classes");

		return updateTableTDO;

	}

	private UpdateTableDTO updateLanguages(Language language) {
		CrudUpdate params = new CrudUpdate();
		UpdateTableDTO crudCheck;

		params.setWhereUsed(true);
		params.setTableName("LanguageTags"); // Lenteles pavadinimas
		params.setConditionColumName("LanguageID"); // pagal koki stulpeli filtruoja pakeitimus
		params.setConditionChangeWhereValueIsEqual(language.id + ""); // kokios eilutes reiksme norim pakeisti

		params.setChangeValueOfColum("Title"); // Kurio stulpelio irasus keisim (Visi isskyrus LanguageId)
		params.setChangeValueTO(language.title); // I kokia reiksme keisim
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
		params.setTableName("Examples"); // Lenteles pavadinimas
		params.setConditionColumName("ExampleId"); // pagal koki stulpeli filtruoja pakeitimus
		params.setConditionChangeWhereValueIsEqual(example.exampleId + ""); // kokios eilutes reiksme norim pakeisti

		params.setChangeValueOfColum("topicId"); // Kurio stulpelio irasus keisim (Visi isskyrus LanguageId)
		params.setChangeValueTO(example.topicId + ""); // I kokia reiksme keisim
		crudCheck = crud.update(params);

		params.setChangeValueOfColum("title"); // Kurio stulpelio irasus keisim (Visi isskyrus LanguageId)
		params.setChangeValueTO(example.title); // I kokia reiksme keisim
		crudCheck = crud.update(params);

		params.setChangeValueOfColum("bodyHtml"); // Kurio stulpelio irasus keisim (Visi isskyrus LanguageId)
		params.setChangeValueTO(example.bodyHtml); // I kokia reiksme keisim
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
		params.setTableName("Topics"); // Lenteles pavadinimas
		params.setConditionColumName("TopicId"); // pagal koki stulpeli filtruoja pakeitimus
		params.setConditionChangeWhereValueIsEqual(topic._TopicId + ""); // kokios eilutes reiksme norim pakeisti

		params.setChangeValueOfColum("Title"); // Kurio stulpelio irasus keisim (Visi isskyrus LanguageId)
		params.setChangeValueTO(topic._TopicTitle); // I kokia reiksme keisim
		crudCheck = crud.update(params);

		params.setChangeValueOfColum("IntroductionHtml"); // Kurio stulpelio irasus keisim (Visi isskyrus LanguageId)
		params.setChangeValueTO(topic._IntroductionHtml); // I kokia reiksme keisim
		crudCheck = crud.update(params);

		params.setChangeValueOfColum("SyntaxHtml"); // Kurio stulpelio irasus keisim (Visi isskyrus LanguageId)
		params.setChangeValueTO(topic._SyntaxHtml); // I kokia reiksme keisim
		crudCheck = crud.update(params);

		params.setChangeValueOfColum("ParametersHtml"); // Kurio stulpelio irasus keisim (Visi isskyrus LanguageId)
		params.setChangeValueTO(topic._ParametersHtml); // I kokia reiksme keisim
		crudCheck = crud.update(params);

		params.setChangeValueOfColum("RemarksHtml"); // Kurio stulpelio irasus keisim (Visi isskyrus LanguageId)
		params.setChangeValueTO(topic._RemarksHtml); // I kokia reiksme keisim
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
		ret.setMessage(
				"ERROR: wrong parameter given! Delete method accepts only instances of Topic, Example and Language classes");

		return ret;
	}

	private DeleteTableDTO deleteLanguage(Language deleteRecord) {

		return crud.delete("LanguageTags", "languageid", deleteRecord.id + "");

	}

	private DeleteTableDTO deleteExample(Example deleteRecord) {

		return crud.delete("Examples", "exampleid", deleteRecord.exampleId + "");

	}

	private DeleteTableDTO deleteTopic(Topic deleteRecord) {
		ExamplesDTO eDTO = getExamplesByTopicId(deleteRecord._TopicId);
		if (eDTO.isSuccess()) {
			for (int i = 0; i < eDTO.getExamples().size(); i++) {
				System.out.println("Example " + crud.delete("Examples", "exampleid", eDTO.getExamples().get(i).exampleId + "").getMessage());
			}
		}
		return crud.delete("Topics", "topicid", deleteRecord._TopicId + "");

	}

	private int findFirstEmptyId(TopicsDTO tdto) {
		int emptyId = 1;
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
