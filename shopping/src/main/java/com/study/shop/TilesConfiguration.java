package com.study.shop;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
 
@Configuration
public class TilesConfiguration {
  @Bean
  public TilesConfigurer tilesConfigurer() {
      final TilesConfigurer configurer = new TilesConfigurer();
      //해당 경로에 tiles.xml 파일을 넣음   //xml 안에 레이아웃 설정
      configurer.setDefinitions(new String[]{"classpath:/tiles/tiles_member.xml",
    		  								 "classpath:/tiles/tiles_notice.xml",
    		  								 "classpath:/tiles/tiles_order.xml",
              								 "classpath:/tiles/tiles_contents.xml"}); // tiles의 경로 --> tiles를 찾는다
      configurer.setCheckRefresh(true);
      return configurer;
  }
 
  @Bean
  public TilesViewResolver tilesViewResolver() {   //view 안에 있는 tiles를 찾는다
      final TilesViewResolver tilesViewResolver = new TilesViewResolver();
      tilesViewResolver.setViewClass(TilesView.class);
      return tilesViewResolver;
  }
}
