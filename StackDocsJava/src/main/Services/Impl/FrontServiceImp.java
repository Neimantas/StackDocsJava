package Services.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import Models.Business.Example;
import Models.Business.Language;
import Models.Business.LanguageTag;
import Models.Business.Topic;
import Models.DAL.ExamplesDAL;
import Models.DAL.LanguageTagsDAL;
import Models.DAL.TopicsDAL;
import Models.DTO.CreateTableDTO;
import Models.DTO.DeleteTableDTO;
import Models.DTO.ExamplesDTO;
import Models.DTO.ExamplesFrontDTO;
import Models.DTO.LanguageTagDTO;
import Models.DTO.LanguageTagFrontDTO;
import Models.DTO.TopicsDTO;
import Models.DTO.TopicsFrontDTO;
import Models.DTO.TopicsInfoFrontDTO;
import Models.DTO.UpdateTableDTO;
import Models.Mapping.ExamplesDALtoExample;
import Models.Mapping.LanguageTagsDALtoLanguage;
import Models.Mapping.TopicsDALtoTopic;
import Services.IFrontService;
import Services.IHigherService;

@Singleton
public class FrontServiceImp implements IFrontService {

	IHigherService hService;

	public FrontServiceImp(HigherServiceImpl hServiceImpl) {
		hService = hServiceImpl;
	}

	@Override
	public TopicsInfoFrontDTO getTopicInfoByTopicId(int topicId) {
		TopicsDTO tInfoDTO = hService.getTopicInfoByTopicId(topicId);
		if (tInfoDTO.isSuccess()) {
			List<TopicsDAL> topicsListas = tInfoDTO.getTopics();
			if (topicsListas.size() == 0) {
				return new TopicsInfoFrontDTO(false, "Tuscias topic", null);
			} else {
				List<Topic> topicsInfoFront = new ArrayList<Topic>();
				for (TopicsDAL topicDal : topicsListas) {
					Topic topic = new Topic();
					TopicsDALtoTopic topicInfo = new TopicsDALtoTopic();
					topicInfo.topicInfoDaltoTopicInfo(topicDal, topic);
					topicsInfoFront.add(topic);
				}
				return new TopicsInfoFrontDTO(true, "success", topicsInfoFront);
			}
		}
		return new TopicsInfoFrontDTO(false, tInfoDTO.getMessage(), null);
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------
	// ------------------------------------------------------------------------------------------------------------------------------------------

	@Override
	public TopicsFrontDTO getTopicsByLanguageId(int languageId, String topicWord) {

		if (languageId == 0) {
			TopicsDTO allTitleDTO = hService.getAllTopics();
			String topicWord2 = topicWord.toLowerCase();
			if (allTitleDTO.isSuccess()) {
				if (topicWord2 != "") {
					List<Topic> topicsFront = new ArrayList<Topic>();
					List<TopicsDAL> topicsListas = allTitleDTO.getTopics();
					for (TopicsDAL topicDal : topicsListas) {
						String textCheck = topicDal.title.toLowerCase();
						if (textCheck.contains(topicWord2)) {
							Topic topic = new Topic();
							TopicsDALtoTopic topicTag = new TopicsDALtoTopic();
							topicTag.topicsTagsDaltoTopicTags(topicDal, topic);
							topicsFront.add(topic);
						}
					}
					return new TopicsFrontDTO(true, "success", topicsFront);
				} else {
					List<Topic> topicsFront = new ArrayList<Topic>();
					List<TopicsDAL> topicsListas = allTitleDTO.getTopics();
					for (TopicsDAL topicDal : topicsListas) {
						String textCheck = topicDal.title.toLowerCase();
						Topic topic = new Topic();
						TopicsDALtoTopic topicTag = new TopicsDALtoTopic();
						topicTag.topicsTagsDaltoTopicTags(topicDal, topic);
						LanguageTagDTO ldto = hService.getLanguageTagByLanguageId(topicDal.languageId);
						// sito mebus >>>>>>>>>>>>>>>>>>>>>>>>
						if (ldto.isSuccess()) {
							topic.set_LanguageTitle(ldto.getLanguageTag().get(0).title);
						}
						// sito mebus >>>>>>>>>>>>>>>>>>>>>>>>
						topicsFront.add(topic);
					}
					return new TopicsFrontDTO(true, "success", topicsFront);
				}

			}
			return new TopicsFrontDTO(false, hService.getAllTopics().getMessage(), null);
		}
		// ---------------------------------------------------------------------------------------------------------------
		else {
			TopicsDTO tDTO = hService.getTopicsByLanguageId(languageId);
			String topicWord2 = topicWord.toLowerCase();
			if (tDTO.isSuccess()) {
				List<TopicsDAL> topicsListas = tDTO.getTopics();
				if (topicsListas.size() == 0) {
					return new TopicsFrontDTO(false, "Nerastas topic", null);
				} else {
					if (topicWord2 != "") {
						List<Topic> topicsFront = new ArrayList<Topic>();
						for (TopicsDAL topicDal : topicsListas) {
							Topic topic = new Topic();
							TopicsDALtoTopic topicTag = new TopicsDALtoTopic();
							topicTag.topicsTagsDaltoTopicTags(topicDal, topic);
							// mapperTopics.map(topicDal, topic);
							String textCheck = topic._TopicTitle.toLowerCase();
							if (textCheck.contains(topicWord2)) {
								topicsFront.add(topic);
							}

						}
						return new TopicsFrontDTO(true, "success", topicsFront);
					} else {
						List<Topic> topicsFront = new ArrayList<Topic>();
						for (TopicsDAL topicDal : topicsListas) {
							Topic topic = new Topic();
							TopicsDALtoTopic topicTag = new TopicsDALtoTopic();
							topicTag.topicsTagsDaltoTopicTags(topicDal, topic);
							topicsFront.add(topic);
						}
						return new TopicsFrontDTO(true, "success", topicsFront);
					}
				}
			}
			return new TopicsFrontDTO(false, tDTO.getMessage(), null);
		}

	}

	// ---------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------

	@Override
	public ExamplesFrontDTO getExamplesByID(int topicId) {
		ExamplesDTO example = hService.getExamplesByTopicId(topicId);
		if (example.isSuccess()) {
			List<Example> exampleFront = new ArrayList<Example>();
			for (ExamplesDAL ex : example.getExamples()) {
				Example exampl = new Example();
				ExamplesDALtoExample mapEx = new ExamplesDALtoExample();
				mapEx.dalToExamples(ex, exampl);
				exampleFront.add(exampl);
			}
			return new ExamplesFrontDTO(true, "success", exampleFront);

		}

		return new ExamplesFrontDTO(false, "Stringo Front Sevice exmaple dalis", null);
	}
	// ---------------------------------------------------------------------------------------------------------------------
	// ---------------------------------------------------------------------------------------------------------------------

	@Override
	public LanguageTagFrontDTO languageTagByLanguageId(int languageId) {
		LanguageTagDTO ldto = hService.getLanguageTagByLanguageId(languageId);
		if (ldto.isSuccess()) {
			List<Language> languageTagFront = new ArrayList<Language>();
			for (LanguageTagsDAL languageTagDto : ldto.getLanguageTag()) {
				Language languageTag = new Language();
				LanguageTagsDALtoLanguage mapll = new LanguageTagsDALtoLanguage();
				mapll.languageDaltoLanguage(languageTagDto, languageTag);
				languageTagFront.add(languageTag);
			}
			return new LanguageTagFrontDTO(true, "success", languageTagFront);
		}

		return new LanguageTagFrontDTO(false, "Stringo Front Sevice Language Tag dalis", null);
	}

	// -------------------------------------------------------------------------------------------------------
	// ------------------------------Delete-----------------------------------------------------------
	@Override
	public DeleteTableDTO deleteLanguage(int languageid) {
		
		return hService.delete(languageTagByLanguageId(languageid)._LanguageTag.get(0));
	}

	@Override
	public DeleteTableDTO deleteExample(int example) {
		return hService.delete(getExamplesByID(example).get_Examples().get(0));
	}

	@Override
	public DeleteTableDTO deleteTopic(int topicID) {
		return hService.delete(getTopicInfoByTopicId(topicID).get_Topics().get(0));
	}

	
	// -------------------------------------------------------------------------------------------------------
	// ------------------------------Create-----------------------------------------------------------
	@Override
	public CreateTableDTO createTopic(List<String> params) {
		Topic ret = new Topic();
		
		ret._LanguageId = Integer.parseInt(params.get(0));
		ret._TopicTitle = params.get(1);
		ret._IntroductionHtml = params.get(2);
		ret._SyntaxHtml = params.get(3);
		ret._ParametersHtml = params.get(4);
		ret._RemarksHtml = params.get(5);
		
		return hService.create(ret);
	}

	
	// -------------------------------------------------------------------------------------------------------
	// ------------------------------Update-----------------------------------------------------------
	@Override
	public UpdateTableDTO updateTopic(List<String> params) {
		Topic ret = new Topic();
		
		ret._LanguageId = Integer.parseInt(params.get(0));
		ret._TopicTitle = params.get(1);
		ret._TopicId = Integer.parseInt(params.get(2));
		ret._IntroductionHtml = params.get(3);
		ret._SyntaxHtml = params.get(4);
		ret._ParametersHtml = params.get(5);
		ret._RemarksHtml = params.get(6);
		
		return hService.update(ret);
	}
	
	

}
